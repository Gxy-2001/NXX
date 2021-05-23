package com.net.nxx.service;

import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.dto.Page;

import java.util.List;

/**
 * @Author: XiaYu
 * @Date 2021/5/8 23:35
 */
public interface IdleItemService {
    /**
     * 发布闲置
     * @param idleItemModel
     * @return
     */
    boolean addIdleItem(NxxIdleItem idleItemModel);

    /**
     * 获取某个闲置的信息
     * @param id
     * @return
     */
    NxxIdleItem getIdleItem(Long id);

    /**
     * 获取某个用户的所有闲置信息
     * @param userId
     * @return
     */
    List<NxxIdleItem> getAllIdelItem(Long userId);

    /**
     * 搜索闲置
     * @param findValue
     * @param page
     * @param nums
     * @return
     */
    Page<NxxIdleItem> findIdleItem(String findValue, int page, int nums);

    /**
     * 按分类获取闲置，分页器
     * @param idleLabel
     * @param page
     * @param nums
     * @return
     */
    Page<NxxIdleItem> findIdleItemByLable(int idleLabel, int page, int nums);

    /**
     * 更新闲置的状态信息
     * @param idleItemModel
     * @return
     */
    boolean updateIdleItem(NxxIdleItem idleItemModel);

    Page<NxxIdleItem> adminGetIdleList(int status, int page, int nums) ;
}
