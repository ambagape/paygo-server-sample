package com.gitittech.paygo.integrations.dtos;

public class CustomerIdentificationResponse {

    public CustomerIdentificationResponse() {
    }

    public CustomerIdentificationResponse(String event, Data data) {
        this.event = event;
        this.data = data;
    }

    public String event;
    public Data data;

    public static class Data {
        public String customer_id;
        public String customer_code;
        public String email;

        public Data() {
        }

        public Data(String customer_id, String customer_code, String email) {
            this.customer_id = customer_id;
            this.customer_code = customer_code;
            this.email = email;
        }
    }
}
