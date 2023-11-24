package com.gitittech.paygo.user.impl;

import com.gitittech.paygo.commons.OTPUtil;
import com.gitittech.paygo.message.api.INotification;
import com.gitittech.paygo.message.api.INotificationTemplate;
import com.gitittech.paygo.user.dtos.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserServiceUtil {

    final static String CONFIRMATION_EMAIL_TEMPLATE_ID = "2c324ed6-00e2-11ed-b939-0242ac120002";
    final static String PASSWORD_RESET_TEMPLATE_ID = "076b1834-00e3-11ed-b939-0242ac120002";
    final private INotificationTemplate templateService;
    final private INotification emailService;
    final private INotification smsService;

    OTPUtil otpUtil = new OTPUtil();

    @Autowired
    public UserServiceUtil(INotificationTemplate templateService,
                           @Qualifier("email") INotification emailService,
                           @Qualifier("sms") INotification smsService)
            throws Exception {
        this.templateService = templateService;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String hashIt(String password) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    void sendConfirmationEmail(User user) throws Throwable {
        Integer otp = otpUtil.generateOTP(user.getId());
        Map<String, String> args = new HashMap<>();
        args.put("code", otp.toString());
        var message = templateService.loadFromTemplate(CONFIRMATION_EMAIL_TEMPLATE_ID, user, args);
        emailService.send(message);
    }

    void sendConfirmationPhone(User user) throws Throwable {
        Integer otp = otpUtil.generateOTP(user.getId());
        Map<String, String> args = new HashMap<>();
        args.put("code", otp.toString());
        var message = templateService.loadFromTemplate(CONFIRMATION_EMAIL_TEMPLATE_ID, user, args);
        smsService.send(message);
    }

    void sendPasswordResetEmail(User user) throws Throwable {
        Integer otp = otpUtil.generateOTP(user.getId());
        Map<String, String> args = new HashMap<>();
        args.put("code", otp.toString());
        var message = templateService.loadFromTemplate(PASSWORD_RESET_TEMPLATE_ID, user, args);
        emailService.send(message);
    }

}