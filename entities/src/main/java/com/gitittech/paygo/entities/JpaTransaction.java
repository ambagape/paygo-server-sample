package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.UnModifiableEntity;
import com.gitittech.paygo.entities.accounts.JpaAccount;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

/*TODO: the user is the authorizer or owner of the transaction,
       while the created by is the doer of the transaction*/
@Entity
@Table(name = "transactions")
public class JpaTransaction extends UnModifiableEntity {
   
    @NotNull
    private String remark;
    @JsonIgnore
    @Column(name = "pin")
    private String PIN;

    private String reference;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private JpaAccount account;

    private Long credit;
    
    private Long debit;
    
    @ManyToOne
    @JoinColumn(name = "double_entry_id")
    private JpaDoubleEntry doubleEntry;


    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Long getDebit() {
        return debit;
    }

    public void setDebit(Long debit) {
        this.debit = debit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String pIN) {
        PIN = pIN;
    }

    public JpaDoubleEntry getDoubleEntry() {
        return doubleEntry;
    }

    public void setDoubleEntry(JpaDoubleEntry doubleEntry) {
        this.doubleEntry = doubleEntry;
    }

   
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public JpaAccount getAccount() {
        return this.account;
    }

    public void setAccount(JpaAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "JpaTransaction{"
                + "id='" + id + '\''
                + ", remark='" + remark + '\''
                + ", PIN='" + PIN + '\''
                + ", reference='" + reference + '\''
                + ", recipient=" + account
                + '}';
    }
}
