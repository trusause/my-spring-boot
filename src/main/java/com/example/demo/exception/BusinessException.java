package com.example.demo.exception;

/**
 * @Author: weicl
 * @Date: 2018/11/20 2:33 PM
 * @Version 1.0
 * @Description ${description}
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){};

    public BusinessException(String message){
        super(message);
    }

}
