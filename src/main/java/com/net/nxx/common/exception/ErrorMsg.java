package com.net.nxx.common.exception;


/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
public enum ErrorMsg {

    DATA_NOT_EXIST("未查询到记录！"),
    ACCOUNT_Ban("账号已被封禁"),
    PASSWORD_RESET_ERROR("修改密码失败"),
    PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统错误"),
    REGISTER_ERROR("注册失败"),
    FILE_UPLOAD_ERROR("文件上传失败"),
    COOKIE_ERROR("请重新登录"),
    EMAIL_LOGIN_ERROR("登录失败 账号或密码错误"),
    FAVORITE_EXIT("收藏已存在");

    private String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
