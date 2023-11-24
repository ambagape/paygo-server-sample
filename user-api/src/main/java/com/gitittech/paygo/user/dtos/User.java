package com.gitittech.paygo.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.dtos.BaseDTO;
import com.gitittech.paygo.user.dtos.beneficiaries.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class User extends BaseDTO {

    @Getter(AccessLevel.NONE)
    protected String bvn;
    @NotNull
    @Email(message = "Invalid email provided")
    protected String email;
    @NotNull(message = "Phone not provided")
    @Getter(AccessLevel.NONE)
    protected String phone;
    @NotNull(message = "First name can not be empty")
    protected String firstName;
    @NotNull(message = "Last name can not be empty")
    protected String lastName;
    protected String middleName;
    protected String customerCode;
    protected String fundingAccountId;
    protected String password;
    protected String address;
    protected String city;
    protected String state;
    protected String country;
    @Past(message = "Date of birth must be a date in the past")
    protected Date dateOfBirth;
    protected String gender;
    protected String nextOfKinName;
    protected String nextOfKinAddress;
    protected String NextOfKinPhone;
    @JsonIgnore
    protected Boolean isPhoneVerified = false;
    @JsonIgnore
    protected Boolean isEmailVerified = false;
    @JsonIgnore
    protected Boolean isBvnVerified = false;
    protected String pin;
    protected Boolean isCompletedTour = false;
    protected Boolean isDisabled = false;
    protected BigDecimal balance;

    public String getBvn(){
        if(bvn==null)
            return null;
        return bvn.startsWith("(")? bvn : String.format("(%s)", bvn);
    }

    public String getPhone(){
        if(phone==null)
            return null;
        return phone.startsWith("(")? phone : String.format("(%s)", phone);
    }
}
