package com.net.nxx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * nxx_admin
 * @author
 */
@ApiModel(value="com.net.nxx.model.NxxAdmin")
@Data
public class NxxAdmin implements Serializable {
    /**
     * 自增主键
     */
    @ApiModelProperty(value="自增主键")
    private Long id;

    /**
     * 管理员账号
     */
    @ApiModelProperty(value="管理员账号")
    private String accountNumber;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String adminPassword;

    /**
     * 管理员名字
     */
    @ApiModelProperty(value="管理员名字")
    private String adminName;

    private static final long serialVersionUID = 1L;

}
