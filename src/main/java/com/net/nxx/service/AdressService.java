package com.net.nxx.service;

import com.net.nxx.model.NxxAddress;

import java.util.List;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-30
 */
public interface AdressService {
    /**
     * 获取一个用户的所有地址信息
     * @param userId
     * @return
     */
    List<NxxAddress> getAddressByUser(Long userId);

    /**
     * 获取单个地址的信息
     * @param id
     * @param userId
     * @return
     */
    NxxAddress getAddressById(Long id,Long userId);

    /**
     * 新增地址信息
     * @param nxxAddress
     * @return
     */
    boolean addAddress(NxxAddress nxxAddress);

    /**
     * 修改地址信息
     * @param nxxAddress
     * @return
     */
    boolean updateAddress(NxxAddress nxxAddress);

    /**
     * 删除地址信息
     * @param nxxAddress
     * @return
     */
    boolean deleteAddress(NxxAddress nxxAddress);
}
