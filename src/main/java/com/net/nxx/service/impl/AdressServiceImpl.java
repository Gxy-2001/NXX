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
     * 查询一个用户的地址信息
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
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public NxxAddress getAddressById(Long id, Long userId) {
        NxxAddress nxxAddress = addressDao.selectByPrimaryKey(id);
        return nxxAddress;
    }

    /**
     * 新增地址
     *
     * @param nxxAddress
     * @return
     */
    @Override
    public boolean addAddress(NxxAddress nxxAddress) {
        if (nxxAddress.getDefaultFlag()) {
            NxxAddress a = new NxxAddress();
            a.setDefaultFlag(false);
            a.setUserId(nxxAddress.getUserId());
            addressDao.updateByUserIdSelective(a);
        } else {
            List<NxxAddress> list = addressDao.getDefaultAddress(nxxAddress.getUserId());
            if (null == list || 0 == list.size()) {
                nxxAddress.setDefaultFlag(true);
            }
        }
        return addressDao.insert(nxxAddress) == 1;
    }

    /**
     * 更新地址
     *
     * @param nxxAddress
     * @return
     */
    @Override
    public boolean updateAddress(NxxAddress nxxAddress) {
        if (nxxAddress.getDefaultFlag()) {
            NxxAddress a = new NxxAddress();
            a.setDefaultFlag(false);
            a.setUserId(nxxAddress.getUserId());
            addressDao.updateByUserIdSelective(a);
        } else {
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
     * 删除地址
     *
     * @param nxxAddress
     * @return
     */
    @Override
    public boolean deleteAddress(NxxAddress nxxAddress) {
        return addressDao.deleteByPrimaryKeyAndUser(nxxAddress.getId(), nxxAddress.getUserId()) == 1;

    }
}
