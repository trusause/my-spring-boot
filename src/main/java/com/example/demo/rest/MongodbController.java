package com.example.demo.rest;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.AyUser;
import com.example.demo.model.AyUserAttachmentRel;
import com.example.demo.service.AyUserAttachmentRelService;
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

@Log4j2
@RequestMapping("/mongodb")
@Controller
public class MongodbController {

    @Autowired
    AyUserAttachmentRelService ayUserAttachmentRelService;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public void test3() {
        AyUserAttachmentRel ayUserAttachmentRel = AyUserAttachmentRel.builder()
                .id(1)
                .userId("1")
                .fileName("个人简历.doc")
                .build();
        ayUserAttachmentRelService.save(ayUserAttachmentRel);
        log.info("保存至MongoDB成功！");
    }

}
