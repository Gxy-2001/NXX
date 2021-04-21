package com.net.nxx.dao;

import com.net.nxx.model.NxxUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NxxUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxUserModel record);

    int insertSelective(NxxUserModel record);

    NxxUserModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NxxUserModel record);

    int updateByPrimaryKey(NxxUserModel record);

    NxxUserModel userLogin(@Param("accountNumber") String accountNumber, @Param("userPassword") String userPassword);

    List<NxxUserModel> getUserList();

    List<NxxUserModel> findUserByList(List<Long> idList);

    List<NxxUserModel> getNormalUser(int begin, int nums);

    List<NxxUserModel> getBanUser(int begin, int nums);

    int countNormalUser();

    int countBanUser();

    int updatePassword(@Param("newPassword") String newPassword,
                       @Param("oldPassword") String oldPassword,@Param("id") Long id);
}
