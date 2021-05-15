package com.net.nxx.dao;

import com.net.nxx.model.NxxCarousel;

import java.util.List;

public interface NxxCarouselDao {
    int deleteByPrimaryKey(Integer carouselId);

    int insert(NxxCarousel record);

    int insertSelective(NxxCarousel record);

    NxxCarousel selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(NxxCarousel record);

    int updateByPrimaryKey(NxxCarousel record);

    List<NxxCarousel> getList(int begin, int nums);

    int getTotalCarousels();
}