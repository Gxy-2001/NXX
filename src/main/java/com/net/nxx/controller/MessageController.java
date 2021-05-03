package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxMessage;
import com.net.nxx.service.MessageService;
import com.net.nxx.vo.ResultVo;
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
    public ResultVo sendMessage(@CookieValue("UserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                @RequestBody NxxMessage nxxMessage) {
        nxxMessage.setUserId(Long.valueOf(UserId));
        nxxMessage.setCreateTime(new Date());
        if (messageService.addMessage(nxxMessage)) {
            return ResultVo.success(nxxMessage);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("查看消息")
    @GetMapping("/info")
    public ResultVo getMessage(@RequestParam Long id) {
        return ResultVo.success(messageService.getMessage(id));
    }

    @ApiOperation("拿到一个物品的所有留言")
    @GetMapping("/idle")
    public ResultVo getAllIdleMessage(@RequestParam Long idleId) {
        return ResultVo.success(messageService.getAllIdleMessage(idleId));
    }

    @ApiOperation("查看一个用户的所有留言")
    @GetMapping("/my")
    public ResultVo getAllMyMessage(@CookieValue("UserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String UserId) {
        return ResultVo.success(messageService.getAllMessage(Long.valueOf(UserId)));
    }

    @ApiOperation("删除一条留言")
    @GetMapping("/delete")
    public ResultVo deleteMessage(@CookieValue("UserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                  @RequestParam Long id) {
        if (messageService.deleteMessage(id)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
