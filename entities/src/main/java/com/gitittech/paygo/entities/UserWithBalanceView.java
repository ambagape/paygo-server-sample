package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_with_wallet_view")
public class UserWithBalanceView extends BaseEntity {

    private String bvn;

    private String email;

    private String phone;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "customer_code")
    private String customerCode;

    private String address;
    private String city;
    private String state;
    private String country;
    @Temporal(TemporalType.DATE)
    //@Past(message = "Date of birth must be a date in the past")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String gender;
    @Column(name = "next_of_kin_name")
    private String nextOfKinName;
    @Column(name = "next_of_kin_address")
    private String nextOfKinAddress;
    @Column(name = "next_of_kin_phone")
    private String NextOfKinPhone;
    @JsonIgnore
    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified = false;
    @JsonIgnore
    @Column(name = "is_email_verified")
    private Boolean isEmailVerified = false;
    @JsonIgnore
    @Column(name = "is_bvn_verified")
    private Boolean isBvnVerified = false;

    @Column(name = "is_completed_tour")
    private Boolean isCompletedTour = false;

    @Column(name = "is_disabled")
    private Boolean isDisabled = false;

    @Column(name = "account_id")
    private String accountId;

    private String pin;

    private BigDecimal balance;

    private String password;
    
    public String getBvn() {
        return bvn;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNextOfKinName() {
        return nextOfKinName;
    }

    public String getNextOfKinAddress() {
        return nextOfKinAddress;
    }

    public String getNextOfKinPhone() {
        return NextOfKinPhone;
    }

    public Boolean getPhoneVerified() {
        return isPhoneVerified;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public Boolean getBvnVerified() {
        return isBvnVerified;
    }

    public Boolean getCompletedTour() {
        return isCompletedTour;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public String getPassword() {
        return password;
    }
}
