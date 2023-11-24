package com.gitittech.paygo.user.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordUpdateRequest {

    private String oldPassword;
    @NotBlank(message="new password is mandatory")
    @Min(value=8, message="new password be 8 or more in length")
    private String newPassword;
    @NotBlank(message="email address is mandatory")
    @Email(message="This is not a valid email address")
    private String email;
    private String code;
}
