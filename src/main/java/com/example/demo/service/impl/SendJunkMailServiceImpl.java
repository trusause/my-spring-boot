package com.example.demo.service.impl;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import com.example.demo.service.SendJunkMailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Log4j2
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AyUserService ayUserService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendJunkMail(List<AyUser> ayUserList) {

        try {
            if (ayUserList == null || ayUserList.size() <= 0) {
                return Boolean.FALSE;
            } else {
                ayUserList.stream().forEach(ayUser -> {
                    MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                    try {
                        //邮件发送方
                        mimeMessageHelper.setFrom(from);
                        //邮件标题
                        mimeMessageHelper.setSubject("stepbystep-learn-spriongboot");
                        //邮件接收方
//                        mimeMessageHelper.setTo("changlong.wei@five-star.cn");
                        mimeMessageHelper.setTo("trusause@126.com");
                        //邮件内容
                        mimeMessageHelper.setText(ayUser.getName()+" ,这是我用SpringBoot写的第一个邮件发送程序！");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    //发送邮件
                    this.javaMailSender.send(mimeMessage);
                });
            }
        } catch (Exception e) {
            log.error("SendJunkMail error and ayUser=%s", ayUserList, e);
            e.printStackTrace();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
