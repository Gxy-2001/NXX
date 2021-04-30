package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * nxx_address
 * @author
 */
@ApiModel(value="com.net.nxx.model.NxxAddress")
@Data
public class NxxAddress implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value="收货人姓名")
    private String consigneeName;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value="收货人手机号")
    private String consigneePhone;

    /**
     * 省
     */
    @ApiModelProperty(value="省")
    private String provinceName;

    /**
     * 市
     */
    @ApiModelProperty(value="市")
    private String cityName;

    /**
     * 区
     */
    @ApiModelProperty(value="区")
    private String regionName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value="详细地址")
    private String detailAddress;

    /**
     * 是否默认地址
     */
    @ApiModelProperty(value="是否默认地址")
    private Boolean defaultFlag;

    /**
     * 用户主键id
     */
    @ApiModelProperty(value="用户主键id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}
