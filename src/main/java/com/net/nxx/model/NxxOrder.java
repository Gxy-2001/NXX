package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * nxx_order
 *
 * @author
 */
@ApiModel(value = "com.net.nxx.model.NxxOrder")
@Data
public class NxxOrder implements Serializable {
    /**
     * 自增主键
     */

    @ApiModelProperty(value = "自增主键")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    /**
     * 用户主键id
     */
    @ApiModelProperty(value = "用户主键id")
    private Long userId;

    @ApiModelProperty(value = "用户")
    private NxxUser user;

    /**
     * 闲置物品主键id
     */
    @ApiModelProperty(value = "闲置物品主键id")
    private Long idleId;

    @ApiModelProperty(value = "闲置物品")
    private NxxIdleItem idleItem;

    /**
     * 订单总价
     */
    @ApiModelProperty(value = "订单总价")
    private BigDecimal orderPrice;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Byte paymentStatus;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentWay;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Byte orderStatus;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;

    private static final long serialVersionUID = 1L;
}
