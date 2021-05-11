package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.dao.NxxOrderDao;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.model.NxxOrder;
import com.net.nxx.service.OrderService;
import com.net.nxx.utils.OrderTask;
import com.net.nxx.utils.OrderTaskHandler;
import com.net.nxx.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: XiaYu
 * @Date 2021/5/8 23:48
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private NxxOrderDao orderDao;

    @Resource
    private NxxIdleItemDao idleItemDao;

    /**
     * 新增订单，同时下架闲置
     * 用了事务串行化，后续要优化，修改更新的sql，增加更新条件，而不是在代码中判断条件
     * 业务逻辑可优化，改为支付时才下架。
     * 新功能待做，需要新增订单超时处理
     * （订单超时：
     * 1、重新上架闲置；2、修改订单状态；
     * 3、确保订单取消前不会影响用户的支付，支付前要判断订单状态并加读锁，取消订单时要判断订单状态为未支付才能取消；
     * 4、保证延期任务一定执行，即确保任务不会因为系统异常而消失）
     *
     * @param orderModel
     * @return
     */

    private static HashMap<Integer, ReentrantLock> lockMap = new HashMap<>();

    static {
//        ReentrantLock lock=new ReentrantLock(true);
        for (int i = 0; i < 100; i++) {
            lockMap.put(i, new ReentrantLock(true));
        }
    }

    @Override
    public boolean addOrder(NxxOrder orderModel) {
        NxxIdleItem idleItemModel = idleItemDao.selectByPrimaryKey(orderModel.getIdleId());
        System.out.println(idleItemModel.getIdleStatus());
        if (idleItemModel.getIdleStatus() != 1) {
            return false;
        }
        NxxIdleItem idleItem = new NxxIdleItem();
        idleItem.setId(orderModel.getIdleId());
        idleItem.setUserId(idleItemModel.getUserId());
        idleItem.setIdleStatus((byte) 2);

        int key = (int) (orderModel.getIdleId() % 100);
        ReentrantLock lock = lockMap.get(key);
        boolean flag;
        try {
            lock.lock();
            flag = addOrderHelp(idleItem, orderModel);
        } finally {
            lock.unlock();
        }
        return flag;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean addOrderHelp(NxxIdleItem idleItem, NxxOrder orderModel) {
        NxxIdleItem idleItemModel = idleItemDao.selectByPrimaryKey(orderModel.getIdleId());
        if (idleItemModel.getIdleStatus() != 1) {
            return false;
        }
        if (idleItemDao.updateByPrimaryKeySelective(idleItem) == 1) {
            if (orderDao.insert(orderModel) == 1) {
                orderModel.setOrderStatus((byte) 4);
                //半小时未支付则取消订单
                OrderTaskHandler.addOrder(new OrderTask(orderModel, 30 * 60));
                return true;
            } else {
                throw new RuntimeException();
            }
        }
        return false;
    }

    /**
     * 获取订单信息，同时获取对应的闲置信息
     *
     * @param id
     * @return
     */
    @Override
    public NxxOrder getOrder(Long id) {
        NxxOrder orderModel = orderDao.selectByPrimaryKey(id);
        orderModel.setIdleItem(idleItemDao.selectByPrimaryKey(orderModel.getIdleId()));
        return orderModel;
    }

    /**
     * 更新订单状态，无验证，后期修改为定制的更新sql
     * 后期改为在支付时下架闲置
     *
     * @param orderModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrder(NxxOrder orderModel) {
        //不可修改的信息
        orderModel.setOrderNumber(null);
        orderModel.setUserId(null);
        orderModel.setIdleId(null);
        orderModel.setCreateTime(null);
        if (orderModel.getOrderStatus() == 4) {
            //取消订单,需要优化，减少数据库查询次数
            NxxOrder o = orderDao.selectByPrimaryKey(orderModel.getId());
            if (o.getOrderStatus() != 0) {
                return false;
            }
            NxxIdleItem idleItemModel = idleItemDao.selectByPrimaryKey(o.getIdleId());
            if (idleItemModel.getIdleStatus() == 2) {
                NxxIdleItem idleItem = new NxxIdleItem();
                idleItem.setId(o.getIdleId());
                idleItem.setUserId(idleItemModel.getUserId());
                idleItem.setIdleStatus((byte) 1);
                if (orderDao.updateByPrimaryKeySelective(orderModel) == 1) {
                    if (idleItemDao.updateByPrimaryKeySelective(idleItem) == 1) {
                        return true;
                    } else {
                        throw new RuntimeException();
                    }
                }
                return false;
            } else {
                if (orderDao.updateByPrimaryKeySelective(orderModel) == 1) {
                    return true;
                } else {
                    throw new RuntimeException();
                }
            }
        }
        return orderDao.updateByPrimaryKeySelective(orderModel) == 1;
    }

    /**
     * 获取我的所有订单
     * 同时查询出对应的闲置信息，
     * 未做分页
     * userId建索引
     *
     * @param userId
     * @return
     */
    @Override
    public List<NxxOrder> getMyOrder(Long userId) {
        List<NxxOrder> list = orderDao.getMyOrder(userId);
        if (list.size() > 0) {
            List<Long> idleIdList = new ArrayList<>();
            for (NxxOrder i : list) {
                idleIdList.add(i.getIdleId());
            }
            List<NxxIdleItem> idleItemModelList = idleItemDao.findIdleByList(idleIdList);
            Map<Long, NxxIdleItem> map = new HashMap<>();
            for (NxxIdleItem idle : idleItemModelList) {
                map.put(idle.getId(), idle);
            }
            for (NxxOrder i : list) {
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        return list;
    }

    /**
     * 查询用户卖出的闲置
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<NxxOrder> getMySoldIdle(Long userId) {
        List<NxxIdleItem> list = idleItemDao.getAllIdleItem(userId);
        List<NxxOrder> orderList = null;
        if (list.size() > 0) {
            List<Long> idleIdList = new ArrayList<>();
            for (NxxIdleItem i : list) {
                idleIdList.add(i.getId());
            }
            orderList = orderDao.findOrderByIdleIdList(idleIdList);
            Map<Long, NxxIdleItem> map = new HashMap<>();
            for (NxxIdleItem idle : list) {
                map.put(idle.getId(), idle);
            }
            for (NxxOrder o : orderList) {
                o.setIdleItem(map.get(o.getIdleId()));
            }
        }
        return orderList;
    }

    @Override
    public PageVo<NxxOrder> getAllOrder(int page, int nums) {
        List<NxxOrder> list = orderDao.getAllOrder((page - 1) * nums, nums);
        if (list.size() > 0) {
            List<Long> idleIdList = new ArrayList<>();
            for (NxxOrder i : list) {
                idleIdList.add(i.getIdleId());
            }
            List<NxxIdleItem> idleItemModelList = idleItemDao.findIdleByList(idleIdList);
            Map<Long, NxxIdleItem> map = new HashMap<>();
            for (NxxIdleItem idle : idleItemModelList) {
                map.put(idle.getId(), idle);
            }
            for (NxxOrder i : list) {
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        int count = orderDao.countAllOrder();
        return new PageVo<>(list, count);
    }

    @Override
    public boolean deleteOrder(long id) {
        return orderDao.deleteByPrimaryKey(id) == 1;
    }
}
