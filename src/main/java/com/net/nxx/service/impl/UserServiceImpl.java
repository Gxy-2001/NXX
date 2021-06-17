package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxUserDao;
import com.net.nxx.model.NxxUser;
import com.net.nxx.service.UserService;
import com.net.nxx.VO.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private NxxUserDao userDao;

    /**
     * 查询一个用户的公开信息
     *
     * @param id
     * @return
     */
    @Override
    public NxxUser getUser(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 登录，安全问题未解决
     *
     * @param accountNumber
     * @param userPassword
     * @return
     */
    @Override
    public NxxUser userLogin(String accountNumber, String userPassword) {
        return userDao.userLogin(accountNumber, userPassword);
    }

    /**
     * 注册
     *
     * @param userModel
     * @return
     */
    @Override
    public boolean userSignIn(NxxUser userModel) {
        return userDao.insert(userModel) == 1;
    }

    /**
     * 修改用户公开信息
     *
     * @param userModel
     * @return
     */
    @Override
    public boolean updateUserInfo(NxxUser userModel) {
        return userDao.updateByPrimaryKeySelective(userModel) == 1;
    }

    /**
     * 修改密码
     *
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    @Override
    public boolean updatePassword(String newPassword, String oldPassword, Long id) {
        return userDao.updatePassword(newPassword, oldPassword, id) == 1;
    }

    @Override
    public Page<NxxUser> getUserByStatus(int status, int page, int nums) {
        List<NxxUser> list;
        int count = 0;
        if (status == 0) {
            count = userDao.countNormalUser();
            list = userDao.getNormalUser((page - 1) * nums, nums);
        } else {
            count = userDao.countBanUser();
            list = userDao.getBanUser((page - 1) * nums, nums);
        }
        return new Page<>(list, count);
    }

}
