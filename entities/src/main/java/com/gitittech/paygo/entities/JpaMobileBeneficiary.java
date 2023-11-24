package com.gitittech.paygo.entities;

import javax.persistence.*;

@Entity
public class JpaMobileBeneficiary extends JpaBeneficiary {

    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.phone + "<" + this.email + ">";
    }

}
