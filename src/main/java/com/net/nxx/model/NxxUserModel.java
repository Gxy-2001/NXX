package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * nxx_user
 * @author 
 */
@ApiModel(value="com.net.nxx.model.NxxUserModel")
@Data
public class NxxUserModel implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 账号（手机号）
     */
    @ApiModelProperty(value="账号（手机号）")
    private String accountNumber;

    /**
     * 登录密码
     */
    @ApiModelProperty(value="登录密码")
    private String userPassword;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
     * 注册时间
     */
    @ApiModelProperty(value="注册时间")
    private Date signInTime;

    /**
     * 状态（1代表封禁）
     */
    @ApiModelProperty(value="状态（1代表封禁）")
    private Byte userStatus;

    private static final long serialVersionUID = 1L;
}