package com.buyanne.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <E> Result success() {
        return new Result<>(0, "操作成功", null);
    }

    public static <E> Result success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <E> Result errror(E data) {
        return new Result<>(0, "操作失败", data);
    }
}
