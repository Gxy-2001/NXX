package com.net.nxx.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.net.nxx.common.exception.ErrorMsg;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class Result<T> {

    private Integer status_code;
    private String msg;
    private T data;

    public static Result success() {
        Result result = new Result();
        result.setStatus_code(1);
        return result;
    }


    public static <T> Result success(T data) {
        Result<T> result = new Result<>();
        result.setStatus_code(1);
        result.setData(data);
        return result;
    }


    public static Result fail(ErrorMsg errorMsg) {
        Result result = new Result();
        result.setStatus_code(0);
        result.setMsg(errorMsg.getMsg());
        return result;
    }


    public static <T> Result fail(ErrorMsg errorMsg, T data) {
        Result<T> result = new Result<>();
        result.setStatus_code(0);
        result.setMsg(errorMsg.getMsg());
        result.setData(data);
        return result;
    }


    public Result(Integer status_code, String msg, T data) {
        this.status_code = status_code;
        this.msg = msg;
        this.data = data;
    }

}
