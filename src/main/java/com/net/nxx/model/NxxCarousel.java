package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * nxx_carousel
 * @author 
 */
@ApiModel(value="com.net.nxx.model.NxxCarousel")
@Data
public class NxxCarousel implements Serializable {
    /**
     * 首页轮播图主键id
     */
    @ApiModelProperty(value="首页轮播图主键id")
    private Integer carouselId;

    /**
     * 轮播图
     */
    @ApiModelProperty(value="轮播图")
    private String carouselUrl;

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    @ApiModelProperty(value="点击后的跳转地址(默认不跳转)")
    private String redirectUrl;

    /**
     * 排序值(字段越大越靠前)
     */
    @ApiModelProperty(value="排序值(字段越大越靠前)")
    private Integer carouselRank;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    @ApiModelProperty(value="删除标识字段(0-未删除 1-已删除)")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 创建者id
     */
    @ApiModelProperty(value="创建者id")
    private Integer createUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    /**
     * 修改者id
     */
    @ApiModelProperty(value="修改者id")
    private Integer updateUser;

    private static final long serialVersionUID = 1L;
}