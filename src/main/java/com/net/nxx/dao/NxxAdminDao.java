package com.net.nxx.dao;

import com.net.nxx.model.NxxAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NxxAdminDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxAdmin record);

    int insertSelective(NxxAdmin record);

    NxxAdmin selectByPrimaryKey(Long id);

    List<NxxAdmin> selectByID(String Name);

    int updateByPrimaryKeySelective(NxxAdmin record);

    int updateByPrimaryKey(NxxAdmin record);

    NxxAdmin login(@Param("accountNumber") String accountNumber, @Param("adminPassword") String adminPassword);

    List<NxxAdmin> getList(int begin, int nums);

    int getCount();
}
