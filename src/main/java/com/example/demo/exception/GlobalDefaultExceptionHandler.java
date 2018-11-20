package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: weicl
 * @Date: 2018/11/20 2:39 PM
 * @Version 1.0
 * @Description ${description}
 */

@ControllerAdvice(basePackages = "com.example.demo")
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, Exception e) {
        return ErrorInfo.builder()
                .message(e.getMessage())
                .url(request.getRequestURI())
                .code(ErrorInfo.SUCCESS)
                .build();
    }

}
