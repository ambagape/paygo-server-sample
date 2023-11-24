package com.gitittech.paygo.integrations.dtos;


public class PaymentGatewayTransaction {

    public String event;
    public Data data;

    public PaymentGatewayTransaction() {

    }

    public PaymentGatewayTransaction(Long amount, String ref, String remark, String status, String channel) {
        data = new Data();
        data.amount = amount;
        data.reference = ref;
        data.authorization = new Authorization();
        data.authorization.narration = remark;
        data.status = status;
        data.channel = channel;
    }

    public static class Data {
        public Long id;
        public String reference;
        public String domain;
        public String device_fingerprint;
        public Long amount;
        public String currency;
        public Double fees;
        public String fraud_status;
        public String ip_address;
        public String status;
        public String message;
        public String gateway_response;
        public String paid_at;
        public String created_at;
        public String channel;
        public Authorization authorization;
        public Customer customer;

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", reference='" + reference + '\'' +
                    ", domain='" + domain + '\'' +
                    ", device_fingerprint='" + device_fingerprint + '\'' +
                    ", amount=" + amount +
                    ", currency='" + currency + '\'' +
                    ", fees=" + fees +
                    ", fraud_status='" + fraud_status + '\'' +
                    ", ip_address='" + ip_address + '\'' +
                    ", status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", gateway_response='" + gateway_response + '\'' +
                    ", paid_at='" + paid_at + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", channel='" + channel + '\'' +
                    ", authorization=" + authorization +
                    ", customer=" + customer +
                    '}';
        }
    }

    public static class Customer {
        public Long id;
        public String phone;
        public String email;
        public String first_name;
        public String last_name;
        public String customer_code;
        public String metadata;

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    ", customer_code='" + customer_code + '\'' +
                    ", metadata='" + metadata + '\'' +
                    '}';
        }
    }

    public static class Authorization {
        public String authorization_code;
        public String bin;
        public String last4;
        public String bank;
        public String country_code;
        public String exp_month;
        public String exp_year;
        public String card_type;
        public String channel;
        public String brand;
        public String reusable;
        public String signature;
        public String sender_bank;
        public String sender_bank_account_number;
        public String sender_country;
        public String sender_name;
        public String narration;
        public String receiver_bank_account_number;
        public String receiver_bank;

        @Override
        public String toString() {
            return "Authorization{" +
                    "authorization_code='" + authorization_code + '\'' +
                    ", bin='" + bin + '\'' +
                    ", last4='" + last4 + '\'' +
                    ", bank='" + bank + '\'' +
                    ", country_code='" + country_code + '\'' +
                    ", exp_month='" + exp_month + '\'' +
                    ", exp_year='" + exp_year + '\'' +
                    ", card_type='" + card_type + '\'' +
                    ", channel='" + channel + '\'' +
                    ", brand='" + brand + '\'' +
                    ", reusable='" + reusable + '\'' +
                    ", signature='" + signature + '\'' +
                    ", sender_bank='" + sender_bank + '\'' +
                    ", sender_bank_account_number='" + sender_bank_account_number + '\'' +
                    ", sender_country='" + sender_country + '\'' +
                    ", sender_name='" + sender_name + '\'' +
                    ", narration='" + narration + '\'' +
                    ", receiver_bank_account_number='" + receiver_bank_account_number + '\'' +
                    ", receiver_bank='" + receiver_bank + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PaymentGatewayTransaction{" +
                "event='" + event + '\'' +
                ", data=" + data +
                '}';
    }
}

