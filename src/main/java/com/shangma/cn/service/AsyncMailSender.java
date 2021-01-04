package com.shangma.cn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class AsyncMailSender {
    @Autowired
    JavaMailSender javaMailSender;
    @Async
    public void senderEmail(SimpleMailMessage mimeMessage){
        log.info(Thread.currentThread().getName());
        javaMailSender.send(mimeMessage);
    }
}
