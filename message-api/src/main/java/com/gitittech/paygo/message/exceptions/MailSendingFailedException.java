package com.gitittech.paygo.message.exceptions;

public class MailSendingFailedException extends Exception {

    private static final long serialVersionUID = 1L;
    private int statusCode = 500;

    public MailSendingFailedException(String message) {
        super(message);
    }

    public MailSendingFailedException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
