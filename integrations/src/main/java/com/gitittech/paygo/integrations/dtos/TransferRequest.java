package com.gitittech.paygo.paymentmethod.dtos;

public class TransferRequest {

    public String source = "balance";

    public String reason;

    public Long amount;

    public String recipient;

}
