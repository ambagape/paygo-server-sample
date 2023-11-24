package com.gitittech.paygo.paymentmethod.dtos;

import java.math.BigDecimal;

public class TransferResponse {

    public String status;
    public String message;
    public Data data;

    public static class Data {
        public String reference;
        public String domain;
        public BigDecimal amount;
        public String status;
        public String transfer_code;
    }
}
