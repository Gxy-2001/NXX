package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxOrder;
import com.net.nxx.service.OrderService;
import com.net.nxx.model.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wt
 * @create 2021-05-09
 */
@Api(tags = "OrderController", description = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("/add")
    public Result addOrder(@CookieValue("UserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String UserId,
                           @RequestBody NxxOrder nxxOrder){


        nxxOrder.setOrderNumber(String.valueOf(System.currentTimeMillis()));
        nxxOrder.setCreateTime(new Date());
        nxxOrder.setUserId(Long.valueOf(UserId));
        nxxOrder.setOrderStatus((byte) 0);
        nxxOrder.setPaymentStatus((byte)0);
        if(orderService.addOrder(nxxOrder)){
            return Result.success(nxxOrder);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }


    @ApiOperation("获取订单信息")
    @GetMapping("/info")
    public Result getOrderInfo(@CookieValue("UserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String UserId,
                               @RequestParam Long id){
        NxxOrder nxxOrder=orderService.getOrder(id);
        if(nxxOrder.getUserId().equals(Long.valueOf(UserId))||
                nxxOrder.getIdleItem().getUserId().equals(Long.valueOf(UserId))){
            return Result.success(nxxOrder);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("更新订单信息")
    @PostMapping("/update")
    public Result updateOrder(@CookieValue("UserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String UserId,
                              @RequestBody NxxOrder nxxOrder){
        if(nxxOrder.getPaymentStatus()!=null&&nxxOrder.getPaymentStatus().equals((byte) 1)){
            nxxOrder.setPaymentTime(new Date());
        }
        if(orderService.updateOrder(nxxOrder)){
            return Result.success(nxxOrder);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("我的订单")
    @GetMapping("/my")
    public Result getMyOrder(@CookieValue("UserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String UserId){
        return Result.success(orderService.getMyOrder(Long.valueOf(UserId)));
    }

    @ApiOperation("我卖出的订单")
    @GetMapping("/my-sold")
    public Result getMySoldIdle(@CookieValue("UserId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String UserId){
        return Result.success(orderService.getMySoldIdle(Long.valueOf(UserId)));
    }
}
