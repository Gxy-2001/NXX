package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * nxx_message
 * @author
 */
@ApiModel(value="com.net.nxx.model.NxxMessage")
@Data
public class NxxMessage implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 用户主键id
     */
    @ApiModelProperty(value="用户主键id")
    private Long userId;

    /**
     * 消息来源用户
     */
    @ApiModelProperty(value="消息来源用户")
    private NxxUser From;
    /**
     * 闲置主键id
     */
    @ApiModelProperty(value="闲置主键id")
    private Long idleId;
    /**
     * 闲置物item
     */
    @ApiModelProperty(value="闲置物item")
    private NxxIdleItem idle;
    /**
     * 留言内容
     */
    @ApiModelProperty(value="留言内容")
    private String content;

    /**
     * 留言时间
     */
    @ApiModelProperty(value="留言时间")
    private Date createTime;

    /**
     * 所回复的用户
     */
    @ApiModelProperty(value="所回复的用户")
    private Long toUser;
    /**
     * 被回复的用户实体
     */
    @ApiModelProperty(value="被回复的用户实体")
    private NxxUser toU;
    /**
     * 所回复的留言
     */
    @ApiModelProperty(value="所回复的留言")
    private Long toMessage;

    /**
     * 所回复的留言实体
     */
    @ApiModelProperty(value="所回复的留言实体")
    private NxxMessage toM;

    private static final long serialVersionUID = 1L;
}
