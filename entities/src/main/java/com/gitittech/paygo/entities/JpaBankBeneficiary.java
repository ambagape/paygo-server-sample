package com.gitittech.paygo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class JpaBankBeneficiary extends JpaBeneficiary {
    @NotNull(message = "Bankcode can not be empty")
    @Column(name = "bank_code")
    private String bankCode;
    @NotNull(message = "Account number can not be empty")
    @Size(min = 10, max = 10, message = "Account number must be 10 digits")
    @Column(name = "nuban_number")
    private String nubanNumber;
    @NotNull(message = "Account title can not be empty")
    private String title;
    @Column(name = "payment_platform_recipient_id")
    private String paymentPlatformRecipientId;

    public JpaBankBeneficiary() {

    }

    public JpaBankBeneficiary(String accountNumber, String bankCode, String name) {
        this.title = name;
        this.nubanNumber = accountNumber;
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getNubanNumber() {
        return nubanNumber;
    }

    public void setNubanNumber(String nubanNumber) {
        this.nubanNumber = nubanNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaymentPlatformRecipientId() {
        return paymentPlatformRecipientId;
    }

    public void setPaymentPlatformRecipientId(String paymentPlatformRecipientId) {
        this.paymentPlatformRecipientId = paymentPlatformRecipientId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JpaBankBeneficiary)) {
            return false;
        }
        return this.nubanNumber.equals(((JpaBankBeneficiary) obj).getNubanNumber());
    }

    @Override
    public String toString() {
        return this.nubanNumber + "<" + this.title + ">";
    }
}
