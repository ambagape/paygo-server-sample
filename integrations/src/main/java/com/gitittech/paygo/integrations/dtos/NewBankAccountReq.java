package com.gitittech.paygo.paymentmethod.dtos;

public class NewBankAccountReq {

    public String customer;
    public String preferred_bank;
    public NewBankAccountReq(String customer, String preferred_bank) {
        super();
        this.customer = customer;
        this.preferred_bank = preferred_bank;
    }
}
