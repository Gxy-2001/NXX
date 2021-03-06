package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.service.IdleItemService;
import com.net.nxx.VO.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wt
 * @create 2021-05-09
 */
@Api(tags = "IdleItemController", description = "闲置商品管理")
@RestController
@RequestMapping("idle")
public class IdleItemController {

    @Autowired
    private IdleItemService idleItemService;

    @ApiOperation("发布闲置")
    @PostMapping("add")
    public Result addIdleItem(@CookieValue("UserId") String UserId,
                              @RequestBody NxxIdleItem nxxIdleItem) {
        nxxIdleItem.setUserId(Long.valueOf(UserId));
        nxxIdleItem.setIdleStatus((byte) 1);
        nxxIdleItem.setReleaseTime(new Date());
        System.out.println("IDLE ADD here!!" + nxxIdleItem);

//      nxxIdleItem.setIdlePlace("src/main/resources/static" + nxxIdleItem.getIdlePlace().substring(
//                nxxIdleItem.getIdlePlace().length()-17));

        System.out.println("IDLE ADD here!!" + nxxIdleItem);

        if (idleItemService.addIdleItem(nxxIdleItem)) {
            return Result.success(nxxIdleItem);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("查询闲置信息，同时查出发布者的信息")
    @GetMapping("info")
    public Result getIdleItem(@RequestParam Long id) {
        return Result.success(idleItemService.getIdleItem(id));
    }

    @ApiOperation("查询用户发布的所有闲置")
    @GetMapping("all")
    public Result getAllIdleItem(@CookieValue("UserId")
                                 @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId) {
        return Result.success(idleItemService.getAllIdelItem(Long.valueOf(UserId)));
    }

    @ApiOperation("搜索，分页")
    @GetMapping("find")
    public Result findIdleItem(@RequestParam(value = "findValue", required = false) String findValue,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "nums", required = false) Integer nums) {

        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return Result.success(idleItemService.findIdleItem(findValue, p, n));
    }

    @ApiOperation("分类查询，分页")
    @GetMapping("lable")
    public Result findIdleItemByLable(@RequestParam(value = "idleLabel", required = true) Integer idleLabel,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "nums", required = false) Integer nums) {
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return Result.success(idleItemService.findIdleItemByLable(idleLabel, p, n));
    }

    @ApiOperation("更新闲置信息")
    @PostMapping("update")
    public Result updateIdleItem(@CookieValue("UserId")
                                 @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                 @RequestBody NxxIdleItem nxxIdleItem) {
        nxxIdleItem.setUserId(Long.valueOf(UserId));
        if (idleItemService.updateIdleItem(nxxIdleItem)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
