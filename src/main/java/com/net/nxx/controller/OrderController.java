package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxOrder;
import com.net.nxx.service.OrderService;
import com.net.nxx.utils.IdFactoryUtil;
import com.net.nxx.utils.OrderTaskHandler;
import com.net.nxx.vo.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
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
    public ResultVo addOrder(@CookieValue("UserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String UserId,
                             @RequestBody NxxOrder nxxOrder){
        if(OrderTaskHandler.orderService==null){
            OrderTaskHandler.orderService=orderService;
        }


        nxxOrder.setOrderNumber(IdFactoryUtil.getOrderId());
        nxxOrder.setCreateTime(new Date());
        nxxOrder.setUserId(Long.valueOf(UserId));
        nxxOrder.setOrderStatus((byte) 0);
        nxxOrder.setPaymentStatus((byte)0);
        if(orderService.addOrder(nxxOrder)){
            return ResultVo.success(nxxOrder);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }


    @ApiOperation("获取订单信息")
    @GetMapping("/info")
    public ResultVo getOrderInfo(@CookieValue("UserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                 @RequestParam Long id){
        NxxOrder nxxOrder=orderService.getOrder(id);
        if(nxxOrder.getUserId().equals(Long.valueOf(UserId))||
                nxxOrder.getIdleItem().getUserId().equals(Long.valueOf(UserId))){
            return ResultVo.success(nxxOrder);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("更新订单信息")
    @PostMapping("/update")
    public ResultVo updateOrder(@CookieValue("UserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String UserId,
                             @RequestBody NxxOrder nxxOrder){
        if(nxxOrder.getPaymentStatus()!=null&&nxxOrder.getPaymentStatus().equals((byte) 1)){
            nxxOrder.setPaymentTime(new Date());
        }
        if(orderService.updateOrder(nxxOrder)){
            return ResultVo.success(nxxOrder);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("我的订单")
    @GetMapping("/my")
    public ResultVo getMyOrder(@CookieValue("UserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String UserId){
        return ResultVo.success(orderService.getMyOrder(Long.valueOf(UserId)));
    }

    @ApiOperation("我卖出的订单")
    @GetMapping("/my-sold")
    public ResultVo getMySoldIdle(@CookieValue("UserId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String UserId){
        return ResultVo.success(orderService.getMySoldIdle(Long.valueOf(UserId)));
    }
}
