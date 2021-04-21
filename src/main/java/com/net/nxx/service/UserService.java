package com.net.nxx.service;

import com.net.nxx.model.NxxUserModel;
import com.net.nxx.vo.PageVo;

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
    NxxUserModel getUser(Long id);

    /**
     * 登录接口
     * @param accountNumber
     * @param userPassword
     * @return
     */
    NxxUserModel userLogin(String accountNumber, String userPassword);

    /**
     * 注册接口
     * @param userModel
     * @return
     */
    boolean userSignIn(NxxUserModel userModel);

    /**
     * 更新用户信息
     * @param userModel
     * @return
     */
    boolean updateUserInfo(NxxUserModel userModel);

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    boolean updatePassword(String newPassword, String oldPassword,Long id);

    PageVo<NxxUserModel> getUserByStatus(int status, int page , int nums);
}
