package com.gitittech.paygo.user.dtos;

import com.gitittech.paygo.entities.UserWithBalanceView;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWithToken extends UserWithBalanceView {
    private String Authorization;
}
