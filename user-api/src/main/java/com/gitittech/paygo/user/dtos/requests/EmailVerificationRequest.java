package com.gitittech.paygo.user.dtos.requests;

import javax.validation.constraints.NotBlank;

public record EmailVerificationRequest(
        @NotBlank(message = "Verification Code is mandatory")
        String code,
        @NotBlank(message = "Email is mandatory")
        String email) {

}
