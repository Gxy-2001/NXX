package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.dao.NxxUserDao;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.model.NxxUser;
import com.net.nxx.service.IdleItemService;
import com.net.nxx.vo.PageVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiaYu
 * @Date 2021/5/8 23:48
 */

@Service
public class IdleItemServiceImpl implements IdleItemService {
    @Resource
    private NxxIdleItemDao idleItemDao;

    @Resource
    private NxxUserDao userDao;

    /**
     * 发布闲置
     * @param idleItemModel
     * @return
     */
    public boolean addIdleItem(NxxIdleItem idleItemModel) {
        return idleItemDao.insert(idleItemModel) == 1;
    }

    /**
     * 查询闲置信息，同时查出发布者的信息
     * @param id
     * @return
     */
    public NxxIdleItem getIdleItem(Long id) {
        NxxIdleItem idleItemModel=idleItemDao.selectByPrimaryKey(id);
        if(idleItemModel!=null){
            idleItemModel.setUser(userDao.selectByPrimaryKey(idleItemModel.getUserId()));
        }
        return idleItemModel;
    }

    /**
     * 查询用户发布的所有闲置
     * user_id建索引
     * @param userId
     * @return
     */
    public List<NxxIdleItem> getAllIdelItem(Long userId) {
        return idleItemDao.getAllIdleItem(userId);
    }

    /**
     * 搜索，分页
     * 同时查出闲置发布者的信息
     * @param findValue
     * @param page
     * @param nums
     * @return
     */
    public PageVo<NxxIdleItem> findIdleItem(String findValue, int page, int nums) {
        List<NxxIdleItem> list=idleItemDao.findIdleItem(findValue, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(NxxIdleItem i:list){
                idList.add(i.getUserId());
            }
            List<NxxUser> userList=userDao.findUserByList(idList);
            Map<Long,NxxUser> map=new HashMap<>();
            for(NxxUser user:userList){
                map.put(user.getId(),user);
            }
            for(NxxIdleItem i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItem(findValue);
        return new PageVo<>(list,count);
    }

    /**
     * 分类查询，分页
     * 同时查出闲置发布者的信息，代码结构与上面的类似，可封装优化，或改为join查询
     * @param idleLabel
     * @param page
     * @param nums
     * @return
     */
    public PageVo<NxxIdleItem> findIdleItemByLable(int idleLabel, int page, int nums) {
        List<NxxIdleItem> list=idleItemDao.findIdleItemByLable(idleLabel, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(NxxIdleItem i:list){
                idList.add(i.getUserId());
            }
            List<NxxUser> userList=userDao.findUserByList(idList);
            Map<Long,NxxUser> map=new HashMap<>();
            for(NxxUser user:userList){
                map.put(user.getId(),user);
            }
            for(NxxIdleItem i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItemByLable(idleLabel);
        return new PageVo<>(list,count);
    }

    /**
     * 更新闲置信息
     * @param idleItemModel
     * @return
     */
    public boolean updateIdleItem(NxxIdleItem idleItemModel){
        return idleItemDao.updateByPrimaryKeySelective(idleItemModel)==1;
    }

    public PageVo<NxxIdleItem> adminGetIdleList(int status, int page, int nums) {
        List<NxxIdleItem> list=idleItemDao.getIdleItemByStatus(status, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(NxxIdleItem i:list){
                idList.add(i.getUserId());
            }
            List<NxxUser> userList=userDao.findUserByList(idList);
            Map<Long,NxxUser> map=new HashMap<>();
            for(NxxUser user:userList){
                map.put(user.getId(),user);
            }
            for(NxxIdleItem i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItemByStatus(status);
        return new PageVo<>(list,count);
    }
}
