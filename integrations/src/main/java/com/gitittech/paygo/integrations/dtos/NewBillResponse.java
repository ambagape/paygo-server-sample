package com.gitittech.paygo.integrations.dtos;


public class NewBillResponse {

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
        public String phone_number;
        public String amount;
        public String flw_ref;
        public String reference;
    }

}
