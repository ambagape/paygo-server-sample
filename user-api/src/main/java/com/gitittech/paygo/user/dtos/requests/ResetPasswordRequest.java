/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.user.dtos.requests;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author ambag
 */
public record ResetPasswordRequest(
        @NotBlank(message = "Email is mandatory")
        String email) {

}
