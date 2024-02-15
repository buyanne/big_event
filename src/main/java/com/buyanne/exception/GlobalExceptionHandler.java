package com.buyanne.exception;

import com.buyanne.pojo.vo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.errror(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
