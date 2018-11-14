package com.example.demo.message;

import com.example.demo.model.AyMood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:57 PM
 * @Version 1.0
 * @Description 生产者
 */

@Service
public class AyMoodProducer {

    /**
     * 发送消息的工具类，也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, final AyMood ayMood) {
        jmsMessagingTemplate.convertAndSend(destination, ayMood);
    }

}
