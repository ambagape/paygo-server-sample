package com.gitittech.paygo.paymentmethod.dtos;

public class NewBankAccountResponse {
    public String status;
    public Data data;
    private String message;

    public static class Data {
        public Bank bank;
        public String account_name;
        public String account_number;
        public Boolean assigned;
        public String currency;
        public String metadata;
        public String active;
        public Long id;
    }

    public static class Bank {
        public String name;
        public String slug;
        public Long id;
    }

}
