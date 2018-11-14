package com.example.demo.service.impl;

import com.example.demo.message.AyMoodProducer;
import com.example.demo.model.AyMood;
import com.example.demo.repository.AyMoodRepository;
import com.example.demo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:20 PM
 * @Version 1.0
 * @Description ${description}
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Autowired
    AyMoodRepository ayMoodRepository;

    @Autowired
    AyMoodProducer ayMoodProducer;

    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    @Override
    public String asynSave(AyMood ayMood) {
        //往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination, ayMood);
        return "success";
    }
}
