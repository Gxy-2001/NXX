package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxAddressDao;
import com.net.nxx.model.NxxAddress;
import com.net.nxx.service.AdressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-30
 */
@Service
public class AdressServiceImpl implements AdressService {

    @Resource
    private NxxAddressDao addressDao;

    /**
     * 查询一个用户的所有地址信息
     * 数据库对user_id建索引，加速查询
     *
     * @param userId
     * @return
     */
    @Override
    public List<NxxAddress> getAddressByUser(Long userId) {
        return addressDao.getAddressByUser(userId);
    }

    /**
     * 通过地址id查询地址的信息
     * 同时验证用户身份
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public NxxAddress getAddressById(Long id, Long userId) {
        NxxAddress nxxAddress = addressDao.selectByPrimaryKey(id);
        if (userId.equals(nxxAddress.getUserId())) {
            return nxxAddress;
        }
        return null;
    }

    /**
     * 新增地址，默认地址的处理逻辑待优化
     *
     * @param nxxAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAddress(NxxAddress nxxAddress) {
        if (nxxAddress.getDefaultFlag()) {
            NxxAddress a = new NxxAddress();
            a.setDefaultFlag(false);
            a.setUserId(nxxAddress.getUserId());
            //将一个用户的其他所有地址改为非默认地址，需要优化，sql增加判断条件default_flag=1，减少更新记录的数目
            addressDao.updateByUserIdSelective(a);
        } else {
            //判断是否有默认地址，若无，则将当前地址设为默认地址
            List<NxxAddress> list = addressDao.getDefaultAddress(nxxAddress.getUserId());
            if (null == list || 0 == list.size()) {
                nxxAddress.setDefaultFlag(true);
            }
        }
        return addressDao.insert(nxxAddress) == 1;
    }

    /**
     * 更新地址信息，同时要验证用户身份（未验证）
     *
     * @param nxxAddress
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAddress(NxxAddress nxxAddress) {
        if (nxxAddress.getDefaultFlag()) {
            //同新增地址时的逻辑
            NxxAddress a = new NxxAddress();
            a.setDefaultFlag(false);
            a.setUserId(nxxAddress.getUserId());
            addressDao.updateByUserIdSelective(a);
        } else {
            //若取消默认地址，则将第一个地址设置为默认地址
            List<NxxAddress> list = addressDao.getAddressByUser(nxxAddress.getUserId());
            for (NxxAddress a : list) {
                if (a.getDefaultFlag() && a.getId().equals(nxxAddress.getId())) {
                    NxxAddress a1 = new NxxAddress();
                    a1.setId(list.get(0).getId());
                    a1.setDefaultFlag(true);
                    return addressDao.updateByPrimaryKeySelective(nxxAddress) == 1 &&
                            addressDao.updateByPrimaryKeySelective(a1) == 1;
                }
            }
        }
        return addressDao.updateByPrimaryKeySelective(nxxAddress) == 1;
    }

    /**
     * 删除地址，同时要验证用户身份
     *
     * @param nxxAddress
     * @return
     */
    @Override
    public boolean deleteAddress(NxxAddress nxxAddress) {
        return addressDao.deleteByPrimaryKeyAndUser(nxxAddress.getId(), nxxAddress.getUserId()) == 1;

    }
}
