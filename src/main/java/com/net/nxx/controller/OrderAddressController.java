package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxOrderAddress;
import com.net.nxx.service.OrderAddressService;
import com.net.nxx.vo.ResultVo;
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
    public ResultVo addOrderAddress(@CookieValue("UserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                    @RequestBody NxxOrderAddress nxxOrderAddress) {
        return ResultVo.success(orderAddressService.addOrderAddress(nxxOrderAddress));
    }
    @ApiOperation("订单更新地址")
    @PostMapping("/update")
    public ResultVo updateOrderAddress(@CookieValue("UserId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                       @RequestBody NxxOrderAddress nxxOrderAddress) {
        if (orderAddressService.updateOrderAddress(nxxOrderAddress)) {
            return ResultVo.success(nxxOrderAddress);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
    @ApiOperation("订单地址表")
    @GetMapping("/info")
    public ResultVo getOrderAddress(@CookieValue("UserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                    @RequestParam Long orderId) {
        return ResultVo.success(orderAddressService.getOrderAddress(orderId));
    }
}