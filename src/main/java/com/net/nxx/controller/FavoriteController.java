package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxFavorite;
import com.net.nxx.service.FavoriteService;
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
 * @create: 2021-05-02
 */
@Api(tags = "FavoriteController", description = "收藏管理")
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation("添加收藏")
    @PostMapping("/add")
    public Result addFavorite(@CookieValue("UserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String UserId,
                              @RequestBody NxxFavorite nxxFavorite) {
        nxxFavorite.setUserId(Long.valueOf(UserId));
        nxxFavorite.setCreateTime(new Date());
        if (favoriteService.addFavorite(nxxFavorite)) {
            return Result.success(nxxFavorite.getId());
        }
        return Result.fail(ErrorMsg.FAVORITE_EXIT);
    }

    @ApiOperation("删除收藏")
    @GetMapping("/delete")
    public Result deleteFavorite(@CookieValue("UserId")
                                   @NotNull(message = "登录异常 请重新登录")
                                   @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                 @RequestParam Long id) {
        if (favoriteService.deleteFavorite(id)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("检查收藏")
    @GetMapping("/check")
    public Result checkFavorite(@CookieValue("UserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                @RequestParam Long idleId) {
        return Result.success(favoriteService.isFavorite(Long.valueOf(UserId), idleId));
    }

    @ApiOperation("我的收藏")
    @GetMapping("/my")
    public Result getMyFavorite(@CookieValue("UserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String UserId) {
        return Result.success(favoriteService.getAllFavorite(Long.valueOf(UserId)));
    }

}
