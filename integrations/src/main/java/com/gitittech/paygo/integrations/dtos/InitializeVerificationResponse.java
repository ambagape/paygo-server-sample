package com.gitittech.paygo.paymentmethod.dtos;

public class InitializeVerificationResponse {

    public Boolean status;

    public String message;

    public Data data;

    public static class Data {

        public String authorization_url;

        public String access_code;

        public String reference;
    }
}
