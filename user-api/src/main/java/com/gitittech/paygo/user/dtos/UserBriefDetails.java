package com.gitittech.paygo.user.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBriefDetails {

    private String name;
    private String phone;
    private String customerCode;

}
