package com.net.nxx.controller;


import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxUser;
import com.net.nxx.service.UserService;
import com.net.nxx.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册账号
     *
     * @param userModel
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("sign-in")
    public ResultVo signIn(@RequestBody NxxUser userModel) {
        System.out.println(userModel);
        userModel.setSignInTime(new Timestamp(System.currentTimeMillis()));
        if (userModel.getAvatar() == null || "".equals(userModel.getAvatar())) {
            userModel.setAvatar("https://gxy-seec2.oss-cn-beijing.aliyuncs.com/seec2/20210430203443.jpg");
        }
        if (userService.userSignIn(userModel)) {
            return ResultVo.success(userModel);
        }
        return ResultVo.fail(ErrorMsg.REGISTER_ERROR);
    }

    /**
     * 登录，不安全，可伪造id，后期改进
     *
     * @param accountNumber
     * @param userPassword
     * @param response
     * @return
     */
    @ApiOperation("登录")
    //@RequestMapping("login")
    @GetMapping("login")
    public ResultVo login(@RequestParam("accountNumber") @NotEmpty @NotNull String accountNumber,
                          @RequestParam("userPassword") @NotEmpty @NotNull String userPassword,
                          HttpServletResponse response) {
        NxxUser userModel = userService.userLogin(accountNumber, userPassword);
        System.out.println("登录：" + userModel);
        if (null == userModel) {
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        if (userModel.getUserStatus() != null && userModel.getUserStatus().equals((byte) 1)) {
            return ResultVo.fail(ErrorMsg.ACCOUNT_Ban);
        }
        Cookie cookie = new Cookie("UserId", String.valueOf(userModel.getId()));
//        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return ResultVo.success(userModel);
    }


    /**
     * 退出登录
     *
     * @param UserId
     * @param response
     * @return
     */
    @ApiOperation("退出登录")
    //@RequestMapping("logout")
    @GetMapping("logout")
    public ResultVo logout(@CookieValue("UserId")
                           @NotNull(message = "登录异常 请重新登录")
                           @NotEmpty(message = "登录异常 请重新登录") String UserId, HttpServletResponse response) {
        Cookie cookie = new Cookie("UserId", UserId);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResultVo.success();
    }


    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public ResultVo getOneUser(@CookieValue("UserId") @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录")
                                       String id) {
        return ResultVo.success(userService.getUser(Long.valueOf(id)));
    }

    /**
     * 修改用户公开信息
     *
     * @param id
     * @param userModel
     * @return
     */
    @ApiOperation("修改用户信息")
    @PostMapping("/info")
    public ResultVo updateUserPublicInfo(@CookieValue("UserId") @NotNull(message = "登录异常 请重新登录")
                                         @NotEmpty(message = "登录异常 请重新登录")
                                                 String id, @RequestBody NxxUser userModel) {
        userModel.setId(Long.valueOf(id));
        if (userService.updateUserInfo(userModel)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }


    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation("修改密码")
    @GetMapping("/password")
    public ResultVo updateUserPassword(@CookieValue("UserId") @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String id,
                                       @RequestParam("oldPassword") @NotEmpty @NotNull String oldPassword,
                                       @RequestParam("newPassword") @NotEmpty @NotNull String newPassword) {
        if (userService.updatePassword(newPassword, oldPassword, Long.valueOf(id))) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.PASSWORD_RESET_ERROR);
    }
}
