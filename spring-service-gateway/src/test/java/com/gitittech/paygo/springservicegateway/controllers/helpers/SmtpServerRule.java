/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.springservicegateway.controllers.helpers;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import javax.mail.internet.MimeMessage;
import org.junit.rules.ExternalResource;

/**
 *
 * @author ambag
 */
public class SmtpServerRule extends ExternalResource{
    private GreenMail smtpServer;
    private final int port;

    public SmtpServerRule(int port) {
        this.port = port;
        smtpServer = new GreenMail(new ServerSetup(port, null, "smtp"));
        smtpServer.start();
    }
   
    public MimeMessage[] getMessages() {
        return smtpServer.getReceivedMessages();
    }

    public void stop() {
        smtpServer.stop();
    }
    
 
}
