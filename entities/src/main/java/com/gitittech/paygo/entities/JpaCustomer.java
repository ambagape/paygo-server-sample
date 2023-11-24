package com.gitittech.paygo.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("JpaCustomer")
public class JpaCustomer extends JpaUser {
    public JpaCustomer() {
        super();
    }

    public JpaCustomer(String phone, String email, String firstName, String lastName) {
        this.phone = phone;
        this.bvn = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
