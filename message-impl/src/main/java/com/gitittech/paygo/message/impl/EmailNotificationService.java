package com.gitittech.paygo.message.impl;

import com.gitittech.paygo.message.api.INotification;
import com.gitittech.paygo.message.dtos.INotificationMapper;
import com.gitittech.paygo.message.dtos.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("email")
public class EmailNotificationService implements INotification {

    private final NotificationCommandRepository msgRepository;    
    private final JavaMailSender mailSender;
    private final Environment environment;

    @Autowired
    public EmailNotificationService(JavaMailSender mailSender, Environment environment,NotificationCommandRepository msgRepository) throws Exception {
        this.msgRepository = msgRepository;
        this.mailSender =  mailSender;
        this.environment = environment;
    }

    @Override
    public void send(Notification msg) {
        this.recordNotification(msg);
        
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(msg.getRecipient().getEmail());
        message.setSubject(msg.getTitle());
        message.setText(msg.getMessage());
        message.setFrom(environment.getProperty("custom.mail.from-address"));
        mailSender.send(message);
    }

    private void recordNotification(Notification msg) {
        msg.setType(EmailNotificationService.class.getName());
        msgRepository.save(INotificationMapper.INSTANCE.toJpaNotification(msg));
    }

   
}