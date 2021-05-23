package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxCarousel;
import com.net.nxx.service.CarouselService;
import com.net.nxx.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: XiaYu
 * @Date 2021/5/14 21:23
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "CarouselController", description = "轮播图管理")
public class CarouselController {
    @Resource
    CarouselService carouselService;

    /**
     * 列表
     */
    @ApiOperation("获取轮播图列表")
    @GetMapping(value = {"/carousels/list",})
    public Result list(HttpSession session,
                       @RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return Result.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return Result.success(carouselService.getCarouselPage(p, n));
    }

    /**
     * 前端首页获取轮播图__不需要验证登录
     */
    @ApiOperation("前端首页获取轮播图")
    @GetMapping(value = "/carousels/get")
    public Result get(HttpSession session,
                      @RequestParam(value = "page", required = false) Integer page,
                      @RequestParam(value = "nums", required = false) Integer nums) {
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return Result.success(carouselService.getCarouselPage(p, n));
    }

    /**
     * 添加
     */
    @ApiOperation("添加轮播图")
    @PostMapping(value = "/carousels/save")
    public Result save(HttpSession session,
                       @RequestBody NxxCarousel carouselModel) {
        if (session.getAttribute("admin") == null) {
            return Result.fail(ErrorMsg.COOKIE_ERROR);
        }
        carouselModel.setCreateTime(new Date());
        carouselModel.setUpdateTime(new Date());
        if (carouselService.add(carouselModel) != null) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.PARAM_ERROR);
    }


    /**
     * 修改
     */
    @ApiOperation("修改轮播图")
    @PostMapping(value = "/carousels/update")
    public Result update(HttpSession session,
                         @RequestBody NxxCarousel carouselModel) {
        if (session.getAttribute("admin") == null) {
            return Result.fail(ErrorMsg.COOKIE_ERROR);
        }
        carouselModel.setUpdateTime(new Date());
        if (carouselService.updateCarouselInfo(carouselModel)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    /**
     * 详情
     */
    @ApiOperation("查看某轮播图详情")
    @GetMapping("/carousels/info")
    public Result info(HttpSession session,
                       @RequestParam("carouselId") @NotEmpty @NotNull Integer carouselId) {
        if (session.getAttribute("admin") == null) {
            return Result.fail(ErrorMsg.COOKIE_ERROR);
        }
        NxxCarousel carousel = carouselService.getCarouselById(carouselId);
        if (carousel == null) {
            return Result.fail(ErrorMsg.DATA_NOT_EXIST);
        }
        return Result.success(carousel);
    }


    /**
     * 删除
     */
    @ApiOperation("删除轮播图")
    @GetMapping(value = "/carousels/delete")
    public Result delete(HttpSession session,
                         @RequestParam("carouselId") @NotNull @NotEmpty Integer carouselId) {
        if (session.getAttribute("admin") == null) {
            return Result.fail(ErrorMsg.COOKIE_ERROR);
        }
        if (carouselService.deleteCarousel(carouselId)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }



}
