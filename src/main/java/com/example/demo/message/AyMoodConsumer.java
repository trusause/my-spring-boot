package com.example.demo.message;

import com.example.demo.model.AyMood;
import com.example.demo.service.AyMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author: weicl
 * @Date: 2018/11/13 6:01 PM
 * @Version 1.0
 * @Description 消费者
 */

@Component
public class AyMoodConsumer {

    @Autowired
    AyMoodService ayMoodService;

    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text) {
        System.out.println("用户发表说说【" + text + "】成功");
    }

    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyMood ayMood) {
        ayMoodService.save(ayMood);
    }
}
