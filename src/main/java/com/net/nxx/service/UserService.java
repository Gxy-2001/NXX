package com.net.nxx.service;

import com.net.nxx.model.NxxUser;
import com.net.nxx.VO.Page;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
public interface UserService {
    /**
     * 获取某个用户的公开信息
     * @param id
     * @return
     */
    NxxUser getUser(Long id);

    /**
     * 登录接口
     * @param accountNumber
     * @param userPassword
     * @return
     */
    NxxUser userLogin(String accountNumber, String userPassword);

    /**
     * 注册接口
     * @param userModel
     * @return
     */
    boolean userSignIn(NxxUser userModel);

    /**
     * 更新用户信息
     * @param userModel
     * @return
     */
    boolean updateUserInfo(NxxUser userModel);

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    boolean updatePassword(String newPassword, String oldPassword,Long id);

    Page<NxxUser> getUserByStatus(int status, int page , int nums);
}
