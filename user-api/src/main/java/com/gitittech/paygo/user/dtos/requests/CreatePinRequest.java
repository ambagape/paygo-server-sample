package com.gitittech.paygo.user.dtos.requests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@Data
@NoArgsConstructor
public class CreatePinRequest {

    @NotBlank(message = "pin is mandatory")
    @Digits(integer = 6, fraction = 0, message = "pin must be 6 digits")
    private String pin;

    private String oldPin;
}
