package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * nxx_order_address
 * @author 
 */
@ApiModel(value="com.net.nxx.model.NxxOrderAddress")
@Data
public class NxxOrderAddress implements Serializable {
    /**
     * 自增id
     */
    @ApiModelProperty(value="自增id")
    private Long id;

    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id")
    private Long orderId;

    /**
     * 收货人
     */
    @ApiModelProperty(value="收货人")
    private String consigneeName;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String consigneePhone;

    /**
     * 收货地址
     */
    @ApiModelProperty(value="收货地址")
    private String detailAddress;

    private static final long serialVersionUID = 1L;
}