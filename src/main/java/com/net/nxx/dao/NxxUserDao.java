package com.net.nxx.dao;

import com.net.nxx.model.NxxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NxxUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxUser record);

    int insertSelective(NxxUser record);

    NxxUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NxxUser record);

    int updateByPrimaryKey(NxxUser record);

    NxxUser userLogin(@Param("accountNumber") String accountNumber, @Param("userPassword") String userPassword);

    List<NxxUser> getUserList();

    List<NxxUser> findUserByList(List<Long> idList);

    List<NxxUser> getNormalUser(int begin, int nums);

    List<NxxUser> getBanUser(int begin, int nums);

    int countNormalUser();

    int countBanUser();

    int updatePassword(@Param("newPassword") String newPassword,
                       @Param("oldPassword") String oldPassword,@Param("id") Long id);
}
