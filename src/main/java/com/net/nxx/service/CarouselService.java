package com.net.nxx.service;

import com.net.nxx.model.NxxCarousel;
import com.net.nxx.VO.Page;

/**
 * @Author: XiaYu
 * @Date 2021/5/14 21:19
 */
public interface CarouselService {
    Page getCarouselPage(int page, int limit);

    NxxCarousel add(NxxCarousel carousel);

    boolean updateCarouselInfo(NxxCarousel carouselModel);

    NxxCarousel getCarouselById(Integer id);

    boolean deleteCarousel(Integer id);
}
