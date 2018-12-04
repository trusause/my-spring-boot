package com.example.demo.actuator;

import com.example.demo.service.AyUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.AbstractEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: weicl
 * @Date: 2018/11/23 2:12 PM
 * @Version 1.0
 * @Description 此端点用来监控数据库用户使用情况，比如用户总数量、被删除用户数量、活跃用户数量
 */

@Slf4j
@Endpoint(id = "userEndPoints")
//@ConfigurationProperties(prefix = "endpoints.userEndPoints")
public class AyUserEndPoint {

    @Autowired
    AyUserService ayUserService;

    //springboot2.0之前的实现：需要重写AbstractEndpoint<Map<String,Object>>的invoke方法
//    /**
//     * 我们需要通过重写 invoke 方法，返回我们要监控的内容。
//     * 这里我定义了一个 Map，它将返回两个参数，一个是标准的包含时区的当前时间格式，一个是当前时间的时间戳格式
//     * @return
//     */MarketingActivityRequestDTO
//    @Override
//    public Map<String, Object> invoke() {
//
//    }

    //实现二：注解的方式
    @ReadOperation
    public Map<String, Object> getUserCount(@Selector String myName) {
        log.info("myName is" + myName);
        Map<String, Object> map = new HashMap<>();
        map.put("user_num", ayUserService.findUserTotalNum());
        return map;
    }


}
