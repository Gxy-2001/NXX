package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * nxx_favorite
 * @author
 */
@ApiModel(value="com.net.nxx.model.NxxFavorite")
@Data
public class NxxFavorite implements Serializable {
    /**
     * 自增主键id
     */
    @ApiModelProperty(value="自增主键id")
    private Long id;

    /**
     * 加入收藏的时间
     */
    @ApiModelProperty(value="加入收藏的时间")
    private Date createTime;

    /**
     * 用户主键id
     */
    @ApiModelProperty(value="用户主键id")
    private Long userId;

    /**
     * 闲置物主键id
     */
    @ApiModelProperty(value="闲置物主键id")
    private Long idleId;

    /**
     * 对应闲职物
     */
    @ApiModelProperty(value="对应闲职物")
    private NxxIdleItem idleItem;

    private static final long serialVersionUID = 1L;
}
