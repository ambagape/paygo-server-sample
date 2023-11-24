package com.gitittech.paygo.entities;

import com.gitittech.paygo.entities.accounts.JpaAccount;
import javax.persistence.*;

@Entity
public class JpaBiller extends JpaBeneficiary {
    @Column(name = "biller_code")
    private String billerCode;

    public String getBillerCode() {
        return billerCode;
    }

    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    @Override
    public String toString() {
        return billerCode;
    }
}
