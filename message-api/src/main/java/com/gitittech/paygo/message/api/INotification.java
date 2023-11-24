package com.gitittech.paygo.message.api;


import com.gitittech.paygo.message.dtos.Notification;
import org.springframework.stereotype.Service;

@Service
public interface INotification {
    String RECIPEINT_NAME_PARAM = "recipientName";
    String CODE_PARAM = "code";
    String AMOUNT_PARAM = "amt";
    String TYPE_PARAM = "type";
    String TIME_PARAM = "time";
    String REMARK_PARAM = "remark";
    String BENEFICIARY_PARAM = "beneficiary";

    void send(Notification message);
}