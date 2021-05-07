package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxOrderAddressDao;
import com.net.nxx.model.NxxOrderAddress;
import com.net.nxx.service.OrderAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-07
 */
@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Resource
    private NxxOrderAddressDao nxxOrderAddressDao;

    /**
     * 为订单新增地址信息
     *
     * @param NxxOrderAddress
     * @return
     */
    @Override
    public boolean addOrderAddress(NxxOrderAddress NxxOrderAddress) {
        return nxxOrderAddressDao.insert(NxxOrderAddress) == 1;
    }

    /**
     * 更新订单的地址信息，未验证用户身份
     *
     * @param NxxOrderAddress
     * @return
     */
    @Override
    public boolean updateOrderAddress(NxxOrderAddress NxxOrderAddress) {
        NxxOrderAddress.setOrderId(null);
        return nxxOrderAddressDao.updateByPrimaryKeySelective(NxxOrderAddress) == 1;
    }

    /**
     * 获取订单的地址信息
     * orderId建索引
     *
     * @param orderId
     * @return
     */
    @Override
    public NxxOrderAddress getOrderAddress(Long orderId) {
        return nxxOrderAddressDao.selectByOrderId(orderId);
    }
}
