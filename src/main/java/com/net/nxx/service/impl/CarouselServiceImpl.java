package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxCarouselDao;
import com.net.nxx.model.NxxCarousel;
import com.net.nxx.service.CarouselService;
import com.net.nxx.VO.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: XiaYu
 * @Date 2021/5/14 21:30
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private NxxCarouselDao carouselDao;

    /**
     * 分页查询轮播图
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Page<NxxCarousel> getCarouselPage(int page, int limit){
        List<NxxCarousel> list = carouselDao.getList((page - 1) * limit, limit);
        int total = carouselDao.getTotalCarousels();
        return new Page<>(list, total);
    }

    /**
     * 增加轮播图
     * @param carousel
     * @return
     */
    @Override
    public NxxCarousel add(NxxCarousel carousel) {
        NxxCarousel Carousel = new NxxCarousel();
        BeanUtils.copyProperties(carousel, Carousel);
        carouselDao.insert(Carousel);
        return Carousel;
    }

    /**
     * 更新轮播图
     * @param carouselModel
     * @return
     */
    @Override
    public boolean updateCarouselInfo(NxxCarousel carouselModel) {
        return carouselDao.updateByPrimaryKeySelective(carouselModel) == 1;
    }

    /**
     * 查看轮播图
     * @param id
     * @return
     */
    @Override
    public NxxCarousel getCarouselById(Integer id) {
        return carouselDao.selectByPrimaryKey(id);
    }

    /**
     * 删除轮播图
     * @param id
     * @return
     */
    @Override
    public boolean deleteCarousel(Integer id) {
        return carouselDao.deleteByPrimaryKey(id) == 1;
    }
}
