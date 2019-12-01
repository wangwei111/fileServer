package com.wwmust.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler{

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handler(HttpServletRequest request,Exception e) {
        Result result = new Result();
        result.setUri(request.getServletPath());
        result.setMsg(e.getClass().toString() + ": " + e.getLocalizedMessage());
        return result;
    }

}
