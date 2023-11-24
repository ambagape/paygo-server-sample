package com.gitittech.paygo.user.exceptions;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Ambrose Ariagiegbe
 */
public class RemoteServerException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int status;

    public RemoteServerException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
