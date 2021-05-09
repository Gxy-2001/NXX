package com.net.nxx.dao;

import com.net.nxx.model.NxxOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NxxOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxOrder record);

    int insertSelective(NxxOrder record);

    NxxOrder selectByPrimaryKey(Long id);

    List<NxxOrder> getMyOrder(Long userId);

    List<NxxOrder> getAllOrder(int begin, int nums);

    int countAllOrder();

    List<NxxOrder> findOrderByIdleIdList(List<Long> idleIdList);

    int updateByPrimaryKeySelective(NxxOrder record);

    int updateByPrimaryKey(NxxOrder record);
}
