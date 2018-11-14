package com.example.demo.rest;

import com.example.demo.dto.ResultDTO;
import com.example.demo.message.AyMoodProducer;
import com.example.demo.model.AyMood;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyMoodService;
import com.example.demo.service.AyUserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午3:32
 * @since JDK 1.8
 */

@Controller
@RequestMapping("/test")
@Log4j2
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
    @ResponseBody
    @GetMapping("/test1")
    public ResultDTO mySqlTest() {
        String sql = "select id,name,password from ay_user";
        List<AyUser> ayUserList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Nullable
            @Override
            public AyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AyUser ayUser = new AyUser();
                ayUser.setId(resultSet.getInt("id"));
                ayUser.setName(resultSet.getString("name"));
                ayUser.setPassword(resultSet.getString("password"));
                return ayUser;
            }
        });
        System.out.println("查询成功：");
        for (AyUser ayUser : ayUserList) {
            System.out.println("【id】:" + ayUser.getId() + ";【name】：" + ayUser.getName());
        }
        return ResultDTO.builder()
                .data(ayUserList)
                .build();
    }

    @GetMapping("/test2")
    public void mySqlTest2() {
        List<AyUser> ayUserList1 = ayUserService.findAll();
        System.out.println("findAll():" + ayUserList1.size());
        List<AyUser> ayUserList2 = ayUserService.findByName("阿毅");
        System.out.println("findByName():" + ayUserList2.size());
        List<AyUser> ayUserList3 = ayUserService.findByNameLike("阿");
        System.out.println("findByNameLike():" + ayUserList3.size());
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<AyUser> ayUserList4 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn():" + ayUserList4.size());
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<AyUser> ayUserPage = ayUserService.findAll(pageRequest);
        System.out.println("page findAll():" + ayUserPage.getTotalPages() + "/" + ayUserPage.getSize() + "/" + ayUserPage.getTotalElements());
        AyUser ayUser = new AyUser();
        ayUser.setName("test");
        ayUser.setPassword("123");
        ayUserService.save(ayUser);
        List<AyUser> ayUserList5 = ayUserService.findAll();
        System.out.println("新增后-findAll():" + ayUserList5.size());
        ayUserService.delete(7);
        List<AyUser> ayUserList6 = ayUserService.findAll();
        System.out.println("删除后-findAll():" + ayUserList6.size());
    }

    @Test
    @ResponseBody
    @RequestMapping("/test3")
    public String test3(Model model) {
        //查询数据库所有用户
        List<AyUser> ayUserList = ayUserService.findAll();
        model.addAttribute("users", ayUserList);
        return "ayUser";
    }

    @Test
    @ResponseBody
    @RequestMapping("/test4")
    public AyUser test4() {
        AyUser ayUser = new AyUser();
        ayUser.setName("test1");
        ayUser.setPassword("123");
        return ayUserService.save(ayUser);
    }

    @Test
    @ResponseBody
    @RequestMapping("/test5/{id}")
    public AyUser test5(@PathVariable Integer id) {
        return ayUserService.findById(id);
    }

    @Test
    @ResponseBody
    @RequestMapping("/test6")
    public AyUser test5() {
        AyUser ayUser = ayUserService.findByNameAndPassword("阿毅", "123456");
        log.info(ayUser.getId() + ayUser.getName());
        return ayUser;
    }

    @Test
    @ResponseBody
    @GetMapping("/test7")
    public AyMood testAyMood() {
        AyMood ayMood = AyMood.builder()
                .userId("1")
                .praiseNum(0)
                .content("这是我的第1条微信说说！！！")
                .publishTime(new Date()).build();
        AyMood ayMood1 = ayMoodService.save(ayMood);
        return ayMood1;
    }

    @Test
    @ResponseBody
    @GetMapping("/test8")
    public void testActiveMQ() {
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination, "hello,mq!!!");
    }

    @ResponseBody
    @GetMapping("/test9")
    public String testAyMoodAsynSave() {
        AyMood ayMood = AyMood.builder()
                .userId("2")
                .praiseNum(0)
                .content("这是我的第2条微信说说！！！")
                .publishTime(new Date()).build();
        String message = ayMoodService.asynSave(ayMood);
        return "异步发表说说" + message;
    }

}
