package com.example.demo.quartz;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import com.example.demo.service.SendJunkMailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
//@Component
//@Configuration
//@EnableScheduling
public class SendMailQuartz {

    @Autowired
    AyUserService ayUserService;

    @Autowired
    SendJunkMailService sendJunkMailService;

    //每5秒执行一次
    @Scheduled(cron = "*/5 * * * * ?")
    public void reportCurrentByCron() {
        List<AyUser> ayUserList = ayUserService.findAll();
        if (ayUserList == null || ayUserList.size() <= 0) {
            return;
        }
        //发送邮件
        sendJunkMailService.sendJunkMail(ayUserList);
        log.info("（发送邮件）定时器执行了！");
    }

}
