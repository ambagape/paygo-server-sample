package com.gitittech.paygo.integrations.dtos;

public class FlutterWaveBillerValidateRes {
    public String status;
    public String message;
    public Data data;

    public static class Data {
        public String biller_code;
        public String name;
        public String response_code;
        public String address;
        public String response_message;
        public String customer;
        public String product_code;
        public String email;
    }
}
