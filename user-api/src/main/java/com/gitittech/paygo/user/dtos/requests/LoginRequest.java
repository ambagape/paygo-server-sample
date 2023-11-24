package com.gitittech.paygo.user.dtos.requests;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        String email,
        String bvn,
        String phone,
        @NotBlank(message = "Password is mandatory")
        String password) {

}
