package com.gitittech.paygo.entities;

import javax.persistence.*;

@Entity
public class JpaDebitCard extends JpaPaymentMethod {


    @Column(name = "bin")
    private String bin;
    @Column(name = "last_4_digits")
    private String last4Digit;
    private String issuer;
    private String type;
    private String country;
    private String expiry;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLast4Digit() {
        return last4Digit;
    }

    public void setLast4Digit(String last4Digit) {
        this.last4Digit = last4Digit;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "JpaDebitCard{" +
                "modified=" + modified +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", id='" + id + '\'' +
                ", bin='" + bin + '\'' +
                ", last4Digit='" + last4Digit + '\'' +
                ", issuer='" + issuer + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", expiry='" + expiry + '\'' +
                ", owner=" + owner +
                ", authorizationCode='" + authorizationCode + '\'' +
                '}';
    }
}
