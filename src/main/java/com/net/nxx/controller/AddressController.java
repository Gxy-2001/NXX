package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxAddress;
import com.net.nxx.service.AdressService;
import com.net.nxx.VO.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-30
 */
@Api(tags = "AddressController", description = "收货地址管理")
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AdressService addressService;

    @ApiOperation("获取地址")
    @GetMapping("/info")
    public Result getAddress(@CookieValue("UserId")
                             @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                             @RequestParam(value = "id", required = false) Long id) {
        if (null == id) {
            return Result.success(addressService.getAddressByUser(Long.valueOf(UserId)));
        } else {
            return Result.success(addressService.getAddressById(id, Long.valueOf(UserId)));
        }
    }

    @ApiOperation("添加地址")
    @PostMapping("/add")
    public Result addAddress(@CookieValue("UserId")
                             @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                             @RequestBody NxxAddress addressModel) {
        addressModel.setUserId(Long.valueOf(UserId));
        if (addressService.addAddress(addressModel)) {
            return Result.success(addressModel);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("更新地址")
    @PostMapping("/update")
    public Result updateAddress(@CookieValue("UserId")
                                @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                @RequestBody NxxAddress addressModel) {
        addressModel.setUserId(Long.valueOf(UserId));
        if (addressService.updateAddress(addressModel)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("删除地址")
    @PostMapping("/delete")
    public Result deleteAddress(@CookieValue("UserId")
                                @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                @RequestBody NxxAddress addressModel) {
        addressModel.setUserId(Long.valueOf(UserId));
        if (addressService.deleteAddress(addressModel)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
