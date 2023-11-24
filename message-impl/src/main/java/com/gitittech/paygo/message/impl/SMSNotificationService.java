package com.gitittech.paygo.message.impl;

import com.gitittech.paygo.message.api.INotification;
import com.gitittech.paygo.message.dtos.Notification;
import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements INotification {

    @Override
    public void send(Notification message) {
        // TODO Auto-generated method stub
    }

}
