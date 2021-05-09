package com.net.nxx.dao;

import com.net.nxx.model.NxxIdleItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NxxIdleItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxIdleItem record);

    int insertSelective(NxxIdleItem record);

    List<NxxIdleItem> getAllIdleItem(Long userId);

    NxxIdleItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NxxIdleItem record);

    int updateByPrimaryKey(NxxIdleItem record);

    List<NxxIdleItem> findIdleItem(String findValue, int begin, int nums);

    int countIdleItem(String findValue);

    int countIdleItemByLable(int idleLabel);

    List<NxxIdleItem> findIdleItemByLable(int idleLabel, int begin, int nums);

    List<NxxIdleItem> getIdleItemByStatus(int status, int begin, int nums);

    int countIdleItemByStatus(int status);

    List<NxxIdleItem> findIdleByList(List<Long> idList);
}
