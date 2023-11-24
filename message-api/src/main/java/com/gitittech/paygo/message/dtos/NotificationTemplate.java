package com.gitittech.paygo.message.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationTemplate {
    private String type;
    private String title;
    private String message;
}
