package com.example.demo.rest;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Log4j2
@RequestMapping("/redis")
@Controller
public class RedisController {

    public static ObjectMapper objectMapper;

    //    @Resource(name = "redisTemplateInit")
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    AyUserService ayUserService;

    @ResponseBody
    @GetMapping("/test1")
    public void testRedis() {
//        redisTemplate.opsForValue().set("name1", "ay");
//        String name1 = (String) redisTemplate.opsForValue().get("name1");
//        System.out.println("redisTemplate创建的-redisTemplate取的-name1=" + name1);
//
//        redisTemplate.delete("name1");
//        redisTemplate.opsForValue().set("name1", "allen");
//        String name2 = stringRedisTemplate.opsForValue().get("name1");
//        System.out.println("redisTemplate创建的-stringRedisTemplate取的-name2=" + name2);
//
//        stringRedisTemplate.delete("name1");
//        String name3 = (String) redisTemplate.opsForValue().get("name1");
//        System.out.println("redisTemplate创建的-redisTemplate取的-name3=" + name3);
//
//        redisTemplate.opsForValue().set("name4", "韦昌龙");
//        String name4 = (String) redisTemplate.opsForValue().get("name4");
//        System.out.println("redisTemplate创建的-redisTemplate取的-name4=" + name4);
//
//        stringRedisTemplate.opsForValue().set("name5", "艾弗森");
//        String name5 = stringRedisTemplate.opsForValue().get("name5");
//        System.out.println("stringRedisTemplate创建的-stringRedisTemplate取的-name5=" + name5);
//
//        stringRedisTemplate.opsForValue().set("name6", "塔图姆");
//        String name6 = (String) redisTemplate.opsForValue().get("name6");
//        System.out.println("stringRedisTemplate创建的-stringRedisTemplate取的-name6=" + name6);

        AyUser ayUser = new AyUser();
        ayUser.setName("shenzhen");

        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps("test1");
        boundValueOperations.set("test");
        boundValueOperations.set("123");


        redisTemplate.opsForValue().set("test1", "123");
        redisTemplate.opsForValue().set("test2", ayUser);
        stringRedisTemplate.opsForValue().set("test3_0", "{\"id\":null,\"name\":\"shenzhen\",\"password\":null}");
        stringRedisTemplate.opsForValue().set("test3", "{\"@class\":\"com.example.demo.model.AyUser\",\"id\":null,\"name\":\"shenzhen\",\"password\":null}");

        System.out.println(stringRedisTemplate.opsForValue().get("test3_0"));
        AyUser ayUser_2 = (AyUser) redisTemplate.opsForValue().get("test2");
        AyUser ayUser_3 = (AyUser) redisTemplate.opsForValue().get("test3");
//        AyUser ayUser_4 = (AyUser) stringRedisTemplate.opsForValue().get("test3");
        AyUser ayUser_3_0 = JSON.parseObject(stringRedisTemplate.opsForValue().get("test3_0"), AyUser.class);

        System.out.println((String) redisTemplate.opsForValue().get("test1"));
        System.out.println(stringRedisTemplate.opsForValue().get("test1"));
        System.out.println(ayUser_2.getName());
//        System.out.println(stringRedisTemplate.opsForValue().get("test2"));
        System.out.println(ayUser_3.getName());
        System.out.println(stringRedisTemplate.opsForValue().get("test3"));

        System.out.println(ayUser_3_0.getName());

    }


    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody
    public void test2() {
        Long redisUserSize = 0L;
        AyUser ayUser = ayUserService.findById(1);
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为 " + redisUserSize);
        System.out.println("--->>> id:" + ayUser.getId() + " name:" + ayUser.getName());

        AyUser ayUser2 = ayUserService.findById(2);
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为 " + redisUserSize);
        System.out.println("--->>> id:" + ayUser2.getId() + " name:" + ayUser2.getName());

        AyUser ayUser3 = ayUserService.findById(3);
        System.out.println("--->>> id:" + ayUser3.getId() + " name:" + ayUser3.getName());
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为 " + redisUserSize);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @ResponseBody
    public void test3() {
        ayUserService.delete(3);
        log.info("delete success!");
    }

}
