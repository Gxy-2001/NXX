package com.net.nxx.dao;

import com.net.nxx.model.NxxAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NxxAddressDao {
    int deleteByPrimaryKeyAndUser(Long id,Long userId);

    int insert(NxxAddress record);

    int insertSelective(NxxAddress record);

    NxxAddress selectByPrimaryKey(Long id);

    List<NxxAddress> getAddressByUser(Long userId);

    List<NxxAddress> getDefaultAddress(Long userId);

    int updateByPrimaryKeySelective(NxxAddress record);

    int updateByUserIdSelective(NxxAddress record);

    int updateByPrimaryKey(NxxAddress record);
}
