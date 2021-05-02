package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.model.NxxFavorite;
import com.net.nxx.service.FavoriteService;
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
    public ResultVo addFavorite(@CookieValue("UserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                @RequestBody NxxFavorite nxxFavorite) {
        nxxFavorite.setUserId(Long.valueOf(UserId));
        nxxFavorite.setCreateTime(new Date());
        if (favoriteService.addFavorite(nxxFavorite)) {
            return ResultVo.success(nxxFavorite.getId());
        }
        return ResultVo.fail(ErrorMsg.FAVORITE_EXIT);
    }

    @ApiOperation("删除收藏")
    @GetMapping("/delete")
    public ResultVo deleteFavorite(@CookieValue("UserId")
                                   @NotNull(message = "登录异常 请重新登录")
                                   @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                   @RequestParam Long id) {
        if (favoriteService.deleteFavorite(id)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @ApiOperation("检查收藏")
    @GetMapping("/check")
    public ResultVo checkFavorite(@CookieValue("UserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String UserId,
                                  @RequestParam Long idleId) {
        return ResultVo.success(favoriteService.isFavorite(Long.valueOf(UserId), idleId));
    }

    @ApiOperation("我的收藏")
    @GetMapping("/my")
    public ResultVo getMyFavorite(@CookieValue("UserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String UserId) {
        return ResultVo.success(favoriteService.getAllFavorite(Long.valueOf(UserId)));
    }

}
