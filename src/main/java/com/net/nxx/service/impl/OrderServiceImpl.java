package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.dao.NxxOrderDao;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.model.NxxOrder;
import com.net.nxx.service.OrderService;
import com.net.nxx.dto.Page;
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

    private static HashMap<Integer, ReentrantLock> lockMap = new HashMap<>();

    static {
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
    public Page<NxxOrder> getAllOrder(int page, int nums) {
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
        return new Page<>(list, count);
    }

    @Override
    public boolean deleteOrder(long id) {
        return orderDao.deleteByPrimaryKey(id) == 1;
    }
}
