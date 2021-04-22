package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxAdmin;
import com.net.nxx.service.AdminService;
import com.net.nxx.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-22
 */
@RestController
@Api(tags = "AdminController", description = "管理员管理系统")
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("登录")
    @GetMapping("login")
    public ResultVo login(@RequestParam("accountNumber") @NotNull @NotEmpty String accountNumber,
                          @RequestParam("adminPassword") @NotNull @NotEmpty String adminPassword,
                          HttpSession session) {
        NxxAdmin adminModel = adminService.login(accountNumber, adminPassword);
        if (null == adminModel) {
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        session.setAttribute("admin", adminModel);
        return ResultVo.success(adminModel);
    }

    @ApiOperation("登出")
    @GetMapping("loginOut")
    public ResultVo loginOut(HttpSession session) {
        session.removeAttribute("admin");
        return ResultVo.success();
    }

    @ApiOperation("列出admin")
    @GetMapping("list")
    public ResultVo getAdminList(HttpSession session,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return ResultVo.success(adminService.getAdminList(p, n));
    }

    @ApiOperation("admin注册")
    @PostMapping("add")
    public ResultVo addAdmin(HttpSession session,
                             @RequestBody NxxAdmin adminModel) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        if (adminService.register(adminModel) != null) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.PARAM_ERROR);
    }
}
