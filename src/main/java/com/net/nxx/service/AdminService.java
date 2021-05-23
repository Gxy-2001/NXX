package com.net.nxx.service;

import com.net.nxx.model.NxxAdmin;
import com.net.nxx.dto.Page;


/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-22
 */
public interface AdminService {
    /**
     * 根据用户名获取后台管理员
     */
    NxxAdmin getAdminByUserID(String username);

    /**
     * 注册功能
     */
    NxxAdmin register(NxxAdmin admin);

    /**
     * 登录功能
     *
     * @param accountNumber 账号
     * @param adminPassword 密码
     *
     */
    NxxAdmin login(String accountNumber, String adminPassword);



    Page<NxxAdmin> getAdminList(int page, int nums);
}
