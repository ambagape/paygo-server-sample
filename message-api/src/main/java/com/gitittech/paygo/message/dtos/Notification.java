package com.gitittech.paygo.message.dtos;

import com.gitittech.paygo.user.dtos.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notification {

    private User recipient;
    private String title;
    private String message;
    private String status;
    private String type;
}
