package com.example.demo.security;

import com.example.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: weicl
 * @Date: 2018/11/21 3:31 PM
 * @Version 1.0
 * @Description ${description}
 * @EnableWebSecurity：开启Security安全框架
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Spring Security5.0中必须制定密码编码方式，否则会报错:There is no PasswordEncoder mapped for the id "null"
     * <p>
     * 也可以通过非注入的方式实现：添加.passwordEncoder(new BCryptPasswordEncoder())
     * .passwordEncoder(new BCryptPasswordEncoder()).withUser("阿毅").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN")
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return new MessageDigestPasswordEncoder("MD5");
    }

    @Bean
    public CustomUserService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //路由策略和访问权限的简单配置
        http
                .formLogin()                        //启用默认登录页面
                .failureUrl("/login?error")         //登录失败返回URL：/login?error
                .defaultSuccessUrl("/ayUser/test")  //登录成功跳转URL，这里跳转到用户首页
                .permitAll();
        super.configure(http);
    }

    /**
     * 配置内存用户
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
////                .passwordEncoder(new BCryptPasswordEncoder()).withUser("阿毅").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN")
//                .withUser("阿毅").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN")
//                .and()
//                .withUser("阿兰").password("123456").roles("USER");
        auth.userDetailsService(customUserService());
    }

}
