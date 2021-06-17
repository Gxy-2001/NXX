package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxOrderAddress;
import com.net.nxx.service.OrderAddressService;
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
 * @create: 2021-05-07
 */
@Api(tags = "OrderAddressController", description = "订单地址管理")
@RestController
@RequestMapping("/order-address")
public class OrderAddressController {
    @Autowired
    private OrderAddressService orderAddressService;

    @ApiOperation("订单添加地址")
    @PostMapping("/add")
    public Result addOrderAddress(@CookieValue("UserId")
                                  @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                  @RequestBody NxxOrderAddress nxxOrderAddress) {
        return Result.success(orderAddressService.addOrderAddress(nxxOrderAddress));
    }

    @ApiOperation("订单更新地址")
    @PostMapping("/update")
    public Result updateOrderAddress(@CookieValue("UserId")
                                     @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                     @RequestBody NxxOrderAddress nxxOrderAddress) {
        if (orderAddressService.updateOrderAddress(nxxOrderAddress)) {
            return Result.success(nxxOrderAddress);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("订单地址表")
    @GetMapping("/info")
    public Result getOrderAddress(@CookieValue("UserId")
                                  @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                  @RequestParam Long orderId) {
        return Result.success(orderAddressService.getOrderAddress(orderId));
    }
}
