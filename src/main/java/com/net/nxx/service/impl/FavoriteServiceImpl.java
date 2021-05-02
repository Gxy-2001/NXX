package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxFavoriteDao;
import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.model.NxxFavorite;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-02
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    private NxxFavoriteDao nxxFavoriteDao;
    @Resource
    private NxxIdleItemDao idleItemDao;
    /**
     * 新增收藏
     *
     * @param favoriteModel
     * @return
     */
    @Override
    public boolean addFavorite(NxxFavorite favoriteModel) {
        return nxxFavoriteDao.insert(favoriteModel) == 1;
    }

    /**
     * 删除收藏
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteFavorite(Long id) {
        return nxxFavoriteDao.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 判断用户是否收藏某个闲置
     * user_id建索引
     *
     * @param userId
     * @param idleId
     * @return
     */
    @Override
    public Integer isFavorite(Long userId, Long idleId) {
        return nxxFavoriteDao.checkFavorite(userId, idleId);
    }

    /**
     * 查询一个用户的所有收藏
     * 通过where in查询关联的闲置信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<NxxFavorite> getAllFavorite(Long userId) {
        List<NxxFavorite> list = nxxFavoriteDao.getFavorite(userId);
        if (list.size() > 0) {
            List<Long> idleIdList = new ArrayList<>();
            for (NxxFavorite i : list) {
                idleIdList.add(i.getIdleId());
            }
            List<NxxIdleItem> idleItemModelList = idleItemDao.findIdleByList(idleIdList);
            Map<Long, NxxIdleItem> map = new HashMap<>();
            for (NxxIdleItem idle : idleItemModelList) {
                map.put(idle.getId(), idle);
            }
            for (NxxFavorite i : list) {
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        return list;
    }
}
