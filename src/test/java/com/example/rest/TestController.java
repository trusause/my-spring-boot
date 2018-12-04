package com.example.rest;

import com.example.demo.MySpringBootApplication;
import com.example.demo.dto.ResultDTO;
import com.example.demo.message.AyMoodProducer;
import com.example.demo.model.AyMood;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyMoodService;
import com.example.demo.service.AyUserService;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午3:32
 * @since JDK 1.8
 */

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  MySpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AyUserService ayUserService;

    @Autowired
    AyMoodService ayMoodService;

    @Autowired
    AyMoodProducer ayMoodProducer;

    @Test
    public void testAsync() throws Exception {
        log.info("开始测试...");
        long start = System.currentTimeMillis();
        log.info("第一次查询所有用户！");
        Future<List<AyUser>> ayUserList = ayUserService.findAsyncAll();
        log.info("第二次查询所有用户！");
        Future<List<AyUser>> ayUserList2 = ayUserService.findAsyncAll();
        log.info("第三次查询所有用户！");
        Future<List<AyUser>> ayUserList3 = ayUserService.findAsyncAll();
        while (true) {
            if (ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()) {
                break;
            } else {
                Thread.sleep(10);
            }
        }
        long end = System.currentTimeMillis();
        log.info("总共耗时：" + (end - start) + "毫秒");
    }



}
