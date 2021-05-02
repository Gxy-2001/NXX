package com.net.nxx.service;

import com.net.nxx.model.NxxFavorite;

import java.util.List;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-02
 */
public interface FavoriteService {
    /**
     * 添加收藏
     * @param favoriteModel
     * @return
     */
    boolean addFavorite(NxxFavorite favoriteModel);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    boolean deleteFavorite(Long id);

    /**
     * 判断是否收藏
     * @param userId
     * @param idleId
     * @return
     */
    Integer isFavorite(Long userId,Long idleId);

    /**
     * 获取收藏列表
     * @param userId
     * @return
     */
    List<NxxFavorite> getAllFavorite(Long userId);
}
