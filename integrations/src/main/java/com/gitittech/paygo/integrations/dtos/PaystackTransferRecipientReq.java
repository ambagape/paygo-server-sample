package com.gitittech.paygo.integrations.dtos;

public class PaystackTransferRecipientReq {

    public String account_number;
    public String bank_code;
    public String name;
    public String currency;
    public Boolean saveBeneficiary = false;
}
