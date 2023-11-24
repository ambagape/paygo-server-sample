/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.integrations.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author ambag
 */
public record BvnVerificationRequest(
        @Size(min = 10, max = 13, message = "BVN must be 11 digits in length")
        String bvn,
        @NotBlank(message = "first name is mandatory")
        String first_name,
        @NotBlank(message = "last name is mandatory")
        String last_name,
        @NotBlank(message = "account number is mandatory")
        String account_number,
        @NotBlank(message = "bank code is mandatory")
        String bank_code,
        @NotBlank(message = "country code is mandatory")
        String country,
        @NotBlank(message = "type is mandatory")
        String type
        ) {
    
}
