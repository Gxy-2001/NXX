package com.net.nxx.dao;

import com.net.nxx.model.NxxIdleItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NxxIdleItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxIdleItem record);

    int insertSelective(NxxIdleItem record);

    NxxIdleItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NxxIdleItem record);

    int updateByPrimaryKey(NxxIdleItem record);

    List<NxxIdleItem> findIdleByList(List<Long> idList);
}
