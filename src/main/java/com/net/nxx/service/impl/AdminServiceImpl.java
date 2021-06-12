package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxAdminDao;
import com.net.nxx.model.NxxAdmin;
import com.net.nxx.service.AdminService;
import com.net.nxx.VO.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-22
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private NxxAdminDao adminDao;


    @Override
    public NxxAdmin getAdminByUserID(String ID) {
        List<NxxAdmin> adminList = adminDao.selectByID(ID);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public NxxAdmin register(NxxAdmin admin) {
        NxxAdmin Admin = new NxxAdmin();
        BeanUtils.copyProperties(admin, Admin);
        //查询是否有相同账号的用户
        List<NxxAdmin> umsAdminList = adminDao.selectByID(Admin.getAccountNumber());
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
//        String encodePassword = passwordEncoder.encode(Admin.getAdminPassword());
//        Admin.setAdminPassword(encodePassword);
        adminDao.insert(Admin);
        return Admin;
    }


    @Override
    public NxxAdmin login(String accountNumber, String adminPassword) {
        return adminDao.login(accountNumber, adminPassword);
    }

    @Override
    public Page<NxxAdmin> getAdminList(int page, int nums) {
        List<NxxAdmin> list = adminDao.getList((page - 1) * nums, nums);
        int count = adminDao.getCount();
        return new Page<>(list, count);
    }


}
