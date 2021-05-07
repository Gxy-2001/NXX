package com.net.nxx.service;

import com.net.nxx.model.NxxOrderAddress;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-07
 */
public interface OrderAddressService {
    /**
     * 为订单添加地址信息
     * @param orderAddressModel
     * @return
     */
    boolean addOrderAddress(NxxOrderAddress orderAddressModel);

    /**
     * 更新订单的地址信息
     * @param orderAddressModel
     * @return
     */
    boolean updateOrderAddress(NxxOrderAddress orderAddressModel);

    /**
     * 获取订单的地址信息
     * @param orderId
     * @return
     */
    NxxOrderAddress getOrderAddress(Long orderId);
}
