package com.net.nxx.dao;

import com.net.nxx.model.NxxFavorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NxxFavoriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxFavorite record);

    int insertSelective(NxxFavorite record);

    NxxFavorite selectByPrimaryKey(Long id);

    List<NxxFavorite> getFavorite(Long userId);

    Integer checkFavorite(Long userId,Long idleId);

    int updateByPrimaryKeySelective(NxxFavorite record);

    int updateByPrimaryKey(NxxFavorite record);
}
