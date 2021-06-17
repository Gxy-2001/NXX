package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxMessage;
import com.net.nxx.service.MessageService;
import com.net.nxx.VO.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-03
 */
@Api(tags = "MessageController", description = "消息管理(留言)")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation("发送留言")
    @PostMapping("/send")
    public Result sendMessage(@CookieValue("UserId")
                              @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                              @RequestBody NxxMessage nxxMessage) {
        nxxMessage.setUserId(Long.valueOf(UserId));
        nxxMessage.setCreateTime(new Date());
        if (messageService.addMessage(nxxMessage)) {
            return Result.success(nxxMessage);
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("查看消息")
    @GetMapping("/info")
    public Result getMessage(@RequestParam Long id) {
        return Result.success(messageService.getMessage(id));
    }

    @ApiOperation("拿到一个物品的所有留言")
    @GetMapping("/idle")
    public Result getAllIdleMessage(@RequestParam Long idleId) {
        return Result.success(messageService.getAllIdleMessage(idleId));
    }

    @ApiOperation("查看一个用户的所有留言")
    @GetMapping("/my")
    public Result getAllMyMessage(@CookieValue("UserId")
                                  @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId) {
        return Result.success(messageService.getAllMessage(Long.valueOf(UserId)));
    }

    @ApiOperation("删除一条留言")
    @GetMapping("/delete")
    public Result deleteMessage(@CookieValue("UserId")
                                @NotNull(message = "登陆异常") @NotEmpty(message = "登录异常") String UserId,
                                @RequestParam Long id) {
        if (messageService.deleteMessage(id)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
