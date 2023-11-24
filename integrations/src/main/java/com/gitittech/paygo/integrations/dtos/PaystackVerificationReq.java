package com.gitittech.paygo.integrations.dtos;

public class PaystackVerificationReq {


    public String first_name;
    public String last_name;
    public String value;
    public String type;
    public String country;

    public PaystackVerificationReq(String first_name, String last_name, String value, String type, String country) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.value = value;
        this.type = type;
        this.country = country;
    }


}
