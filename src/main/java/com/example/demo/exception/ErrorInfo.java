package com.example.demo.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: weicl
 * @Date: 2018/11/20 2:36 PM
 * @Version 1.0
 * @Description ${description}
 */

@Data
@Builder
public class ErrorInfo<T> {

    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 100;

    /**
     * 错误信息
     */
    private Integer code;

    /**
     * 错误码
     */
    private String message;

    private String url;

    private T data;

}
