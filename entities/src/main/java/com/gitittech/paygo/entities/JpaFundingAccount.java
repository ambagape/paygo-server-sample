package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.UnModifiableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funding_accounts")
public class JpaFundingAccount extends UnModifiableEntity {
    @NotNull(message = "account number can't be empty")
    @Column(name = "account_no")
    private String accountNo;
    @NotNull(message = "account name can't be empty")
    @Column(name = "account_name")
    private String accountName;
    @NotNull(message = "bank code can't be empty")
    @Column(name = "bank_code")
    private String bankCode;
    @NotNull(message = "currency can't be empty")
    private String currency;
    @NotNull(message = "bank name can't be empty")
    @Column(name = "bank_name")
    private String bankName;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


}
