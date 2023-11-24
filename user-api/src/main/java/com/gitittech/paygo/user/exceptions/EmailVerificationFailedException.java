package com.gitittech.paygo.user.exceptions;

@SuppressWarnings("serial")
public class EmailVerificationFailedException extends Exception {

    private final int statusCode;

    public EmailVerificationFailedException(String message) {
        super(message);
        this.statusCode = 400;
    }

    public EmailVerificationFailedException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
