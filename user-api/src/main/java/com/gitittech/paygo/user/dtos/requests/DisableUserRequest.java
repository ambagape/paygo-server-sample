/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.user.dtos.requests;

import javax.validation.constraints.NotNull;

/**
 *
 * @author ambag
 */
public record DisableUserRequest(@NotNull(message = "isDisable must be set to true or false")
        boolean isDisable) {

}
