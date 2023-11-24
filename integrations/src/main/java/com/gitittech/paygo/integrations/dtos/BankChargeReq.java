package com.gitittech.paygo.integrations.dtos;

public class BankChargeReq {

    public String email;
    public String authorization_code;
    public Long amount;
    public Bank bank = new Bank();

    public BankChargeReq(String email, String authorization_code, Long amount, String bankCode, String accountNo) {
        super();
        this.email = email;
        this.authorization_code = authorization_code;
        this.amount = amount;
        bank.code = bankCode;
        bank.account_number = accountNo;
    }

    public static class Bank {

        public String code;
        public String account_number;
    }
}
