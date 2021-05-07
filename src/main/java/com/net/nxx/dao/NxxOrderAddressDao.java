package com.net.nxx.dao;

import com.net.nxx.model.NxxOrderAddress;

public interface NxxOrderAddressDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxOrderAddress record);

    int insertSelective(NxxOrderAddress record);

    NxxOrderAddress selectByPrimaryKey(Long id);

    NxxOrderAddress selectByOrderId(Long orderId);

    int updateByPrimaryKeySelective(NxxOrderAddress record);

    int updateByPrimaryKey(NxxOrderAddress record);
}
