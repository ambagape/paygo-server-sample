package com.gitittech.paygo.paymentmethod.dtos;

public class CardChargeReq {

    public String email;
    public String authorization_code;
    public Long amount;

    public CardChargeReq(String email, String authorization_code, Long amount) {
        super();
        this.email = email;
        this.authorization_code = authorization_code;
        this.amount = amount;
    }
}
