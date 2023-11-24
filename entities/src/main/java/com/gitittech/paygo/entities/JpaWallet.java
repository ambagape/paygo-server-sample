package com.gitittech.paygo.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gitittech.paygo.commons.MoneySerializer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class JpaWallet extends JpaPaymentMethod {

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal balance = new BigDecimal(0);

    @Column(name = "ledger_balance")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal ledgerBalance = new BigDecimal(0);

    public BigDecimal getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(BigDecimal ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
