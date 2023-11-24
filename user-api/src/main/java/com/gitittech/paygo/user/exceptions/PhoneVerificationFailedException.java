package com.gitittech.paygo.user.exceptions;

@SuppressWarnings("serial")
public class PhoneVerificationFailedException extends Exception {

    private final int statusCode;

    public PhoneVerificationFailedException(String message) {
        super(message);
        this.statusCode = 400;
    }

    public PhoneVerificationFailedException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
