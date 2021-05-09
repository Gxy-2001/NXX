package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    @Id
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 闲置物名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @ApiModelProperty(value="闲置物名称")
    private String idleName;

    /**
     * 详情
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @ApiModelProperty(value="详情")
    private String idleDetails;

    /**
     * 图集
     */
    @Field(type = FieldType.Keyword, index = false)
    @ApiModelProperty(value="图集")
    private String pictureList;

    /**
     * 价格
     */
    @Field(type = FieldType.Auto, index = false)
    @ApiModelProperty(value="价格")
    private BigDecimal idlePrice;

    /**
     * 发货地区
     */
    @Field(type = FieldType.Keyword, index = false)
    @ApiModelProperty(value="发货地区")
    private String idlePlace;

    /**
     * 分类标签
     */
    @Field(type = FieldType.Integer, index = false)
    @ApiModelProperty(value="分类标签")
    private Integer idleLabel;

    /**
     * 发布时间
     */
    @Field(type = FieldType.Date, index = false)
    @ApiModelProperty(value="发布时间")
    private Date releaseTime;

    /**
     * 状态（发布1、下架2、删除0）
     */
    @Field(type = FieldType.Byte, index = false)
    @ApiModelProperty(value="状态（发布1、下架2、删除0）")
    private Byte idleStatus;

    /**
     * 用户主键id
     */
    @Field(type = FieldType.Long, index = false)
    @ApiModelProperty(value="用户主键id")
    private Long userId;

    /**
     * 对应用户
     */
    @ApiModelProperty(value="对应用户")
    private NxxUser user;

    private static final long serialVersionUID = 1L;
    //纯测试ES用
    public NxxIdleItem (long id){
        this.id = id;
    }

    public NxxIdleItem(){

    }
}
