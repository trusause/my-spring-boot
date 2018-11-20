package com.example.demo.config;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: weicl
 * @Date: 2018/11/20 10:10 AM
 * @Version 1.0
 * @Description ${description}
 */

//@Component和@Configuration用其一即可
//@Component
@Configuration
public class ErrorPageConfig {

    /**
     * SpringBoot2.0以上版本WebServerFactoryCustomizer代替之前版本的EmbeddedWebServerFactoryCustomizerAutoConfiguration
     *
     * @return
     */

    //@Bean必须加上
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        //第一种：常规写法
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//                factory.addErrorPages(errorPage404);
//            }
//        };
        //第二种写法：lambda写法
        return (factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            factory.addErrorPages(errorPage404);
        });
    }

//    /**
//     * Spring2.0以下版本的写法
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "404.html");
//                container.addErrorPages(errorPage404);
//            }
//        };
//    }

}
