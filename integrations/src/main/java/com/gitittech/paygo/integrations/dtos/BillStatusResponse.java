package com.gitittech.paygo.integrations.dtos;

public class BillStatusResponse {

    public String status;
    public String message;
    public Data data;

    public static class Data {
        public String currency;
        public String frequency;
        public String customer_id;
        public String product;
        public String product_name;
        public String transaction_date;
        public String country;
        public String status;
        public String product_details;
        public String amount;
        public String tx_ref;
        public String extra;
    }


    public static class Verbose {
        public int verbose = 1;
    }
}
