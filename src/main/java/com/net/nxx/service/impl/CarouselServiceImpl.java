package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxCarouselDao;
import com.net.nxx.model.NxxCarousel;
import com.net.nxx.service.CarouselService;
import com.net.nxx.vo.PageVo;
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

    @Override
    public PageVo<NxxCarousel> getCarouselPage(int page, int limit){
        List<NxxCarousel> list = carouselDao.getList((page - 1) * limit, limit);
        int total = carouselDao.getTotalCarousels();
        return new PageVo<>(list, total);
    }

    @Override
    public NxxCarousel add(NxxCarousel carousel) {
        NxxCarousel Carousel = new NxxCarousel();
        BeanUtils.copyProperties(carousel, Carousel);
        carouselDao.insert(Carousel);
        return Carousel;
    }

    @Override
    public boolean updateCarouselInfo(NxxCarousel carouselModel) {
        return carouselDao.updateByPrimaryKeySelective(carouselModel) == 1;
    }

    @Override
    public NxxCarousel getCarouselById(Integer id) {
        return carouselDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteCarousel(Integer id) {
        return carouselDao.deleteByPrimaryKey(id) == 1;
    }
}
