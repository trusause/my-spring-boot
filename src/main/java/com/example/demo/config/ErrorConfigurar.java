package com.example.demo.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: weicl
 * @Date: 2018/11/20 10:58 AM
 * @Version 1.0
 * @Description ${description}
 */

@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

        registry.addErrorPages(errorPages);
    }
}
