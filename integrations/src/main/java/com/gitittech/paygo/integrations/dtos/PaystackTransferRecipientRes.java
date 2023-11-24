package com.gitittech.paygo.integrations.dtos;

public class PaystackTransferRecipientRes {

    public Boolean status;
    public String message;
    public Data data;

    public static class Data {
        public boolean active;
        public Long id;
        public Long integration;
        public String name;
        public String recipient_code;
        public String type;
        public Details details;
    }

    public static class Details {
        public String account_number;
        public String account_name;
        public String bank_code;
        public String bank_name;
    }
}
