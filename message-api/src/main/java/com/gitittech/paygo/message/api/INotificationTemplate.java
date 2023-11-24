/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gitittech.paygo.message.api;

import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.message.dtos.Notification;
import com.gitittech.paygo.user.dtos.User;

import java.util.Map;

/**
 * @author Ambrose Ariagiegbe
 */
public interface INotificationTemplate {

    Notification loadFromTemplate(String tid, User recipient, Map<String, String> additionalParams) throws NotFoundException;
}