package com.gitittech.paygo.integrations.dtos;

public class NewCustomerReq {

    public String email;
    public String first_name;
    public String last_name;

    public NewCustomerReq(String email, String firstName, String lastName) {
        this.email = email;
        first_name = firstName;
        last_name = lastName;
    }
}
