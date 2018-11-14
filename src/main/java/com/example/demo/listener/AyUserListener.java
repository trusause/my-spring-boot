package com.example.demo.listener;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@Log4j2
@Configuration
@WebListener
public class AyUserListener implements ServletContextListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    AyUserService ayUserService;

    public static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        System.out.println("ServletContext 上下文初始化");
        log.info("ServletContext 上下文初始化");
        //查询数据库中的所有用户
        List<AyUser> ayUserList = ayUserService.findAll();
        if (ayUserList.size() > 0) {
            redisTemplate.delete(ALL_USER);
            redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);
            List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
//        System.out.println("缓存中目前的用户数有：" + queryUserList.size() + "人");
            log.info("缓存中目前的用户数有：" + queryUserList.size() + "人");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        System.out.println("ServletContext 上下文销毁");
        log.info("ServletContext 上下文销毁");
    }
}
