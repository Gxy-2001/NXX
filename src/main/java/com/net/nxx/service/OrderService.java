package com.net.nxx.service;

import com.net.nxx.model.NxxOrder;
import com.net.nxx.VO.Page;

import java.util.List;

/**
 * @Author: XiaYu
 * @Date 2021/5/8 23:41
 */
public interface OrderService {
    /**
     * 新增订单
     * @param orderModel
     * @return
     */
    boolean addOrder(NxxOrder orderModel);

    /**
     * 获取订单信息
     * @param id
     * @return
     */
    NxxOrder getOrder(Long id);

    /**
     * 更新订单信息
     * @param orderModel
     * @return
     */
    boolean updateOrder(NxxOrder orderModel);

    /**
     * 获取某个用户买到的闲置的订单列表
     * @param userId
     * @return
     */
    List<NxxOrder> getMyOrder(Long userId);

    /**
     * 获取某个用户卖出的闲置的订单信息
     * @param userId
     * @return
     */
    List<NxxOrder> getMySoldIdle(Long userId);

    Page<NxxOrder> getAllOrder(int page, int nums);

    boolean deleteOrder(long id);
}
