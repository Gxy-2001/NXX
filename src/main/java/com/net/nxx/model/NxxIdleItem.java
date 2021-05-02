package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * nxx_idle_item
 * @author 
 */
@ApiModel(value="com.net.nxx.model.NxxIdleItem")
@Data
public class NxxIdleItem implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 闲置物名称
     */
    @ApiModelProperty(value="闲置物名称")
    private String idleName;

    /**
     * 详情
     */
    @ApiModelProperty(value="详情")
    private String idleDetails;

    /**
     * 图集
     */
    @ApiModelProperty(value="图集")
    private String pictureList;

    /**
     * 价格
     */
    @ApiModelProperty(value="价格")
    private BigDecimal idlePrice;

    /**
     * 发货地区
     */
    @ApiModelProperty(value="发货地区")
    private String idlePlace;

    /**
     * 分类标签
     */
    @ApiModelProperty(value="分类标签")
    private Integer idleLabel;

    /**
     * 发布时间
     */
    @ApiModelProperty(value="发布时间")
    private Date releaseTime;

    /**
     * 状态（发布1、下架2、删除0）
     */
    @ApiModelProperty(value="状态（发布1、下架2、删除0）")
    private Byte idleStatus;

    /**
     * 用户主键id
     */
    @ApiModelProperty(value="用户主键id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}