package com.gitittech.paygo.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class JpaAccountDebit extends JpaPaymentMethod {

    @NotNull(message = "account number can't be empty")
    @Column(name = "account_no")
    private String accountNo;
    @NotNull(message = "account name can't be empty")
    @Column(name = "account_name")
    private String accountName;
    @NotNull(message = "bank code can't be empty")
    @Column(name = "bank_code")
    private String bankCode;

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

}
