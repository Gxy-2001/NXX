package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.service.IdleItemService;
import com.net.nxx.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
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
    public ResultVo addIdleItem(@CookieValue("UserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                @RequestBody NxxIdleItem nxxIdleItem) {
        nxxIdleItem.setUserId(Long.valueOf(UserId));
        nxxIdleItem.setIdleStatus((byte) 1);
        nxxIdleItem.setReleaseTime(new Date());
        System.out.println("IDLE ADD here!!" + nxxIdleItem);
        String l = nxxIdleItem.getPictureList();

//        nxxIdleItem.setIdlePlace("src/main/resources/static" + nxxIdleItem.getIdlePlace().substring(
//                nxxIdleItem.getIdlePlace().length()-17));

        System.out.println("IDLE ADD here!!" + nxxIdleItem);

        if (idleItemService.addIdleItem(nxxIdleItem)) {
            return ResultVo.success(nxxIdleItem);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("查询闲置信息，同时查出发布者的信息")
    @GetMapping("info")
    public ResultVo getIdleItem(@RequestParam Long id) {
        return ResultVo.success(idleItemService.getIdleItem(id));
    }

    @ApiOperation("查询用户发布的所有闲置")
    @GetMapping("all")
    public ResultVo getAllIdleItem(@CookieValue("UserId")
                                   @NotNull(message = "登录异常 请重新登录")
                                   @NotEmpty(message = "登录异常 请重新登录") String UserId) {
        return ResultVo.success(idleItemService.getAllIdelItem(Long.valueOf(UserId)));
    }

    @ApiOperation("搜索，分页，同时查出闲置发布者的信息")
    @GetMapping("find")
    public ResultVo findIdleItem(@RequestParam(value = "findValue", required = false) String findValue,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "nums", required = false) Integer nums) {
        if (null == findValue) {
            findValue = "";
        }
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return ResultVo.success(idleItemService.findIdleItem(findValue, p, n));
    }

    @ApiOperation("分类查询，分页，同时查出闲置发布者的信息，代码结构与上面的类似，可封装优化，或改为join查询")
    @GetMapping("lable")
    public ResultVo findIdleItemByLable(@RequestParam(value = "idleLabel", required = true) Integer idleLabel,
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
        return ResultVo.success(idleItemService.findIdleItemByLable(idleLabel, p, n));
    }

    @ApiOperation("更新闲置信息")
    @PostMapping("update")
    public ResultVo updateIdleItem(@CookieValue("UserId")
                                   @NotNull(message = "登录异常 请重新登录")
                                   @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                   @RequestBody NxxIdleItem nxxIdleItem) {
        nxxIdleItem.setUserId(Long.valueOf(UserId));
        if (idleItemService.updateIdleItem(nxxIdleItem)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
