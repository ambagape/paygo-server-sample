package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;
import com.gitittech.paygo.entities.accounts.JpaAccount;
import java.io.Serializable;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "users")
@DiscriminatorValue("JpaUser")
public class JpaUser extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @Column(unique = true)
    protected String bvn;
    @NotNull
    @Email(message = "Invalid email provided")
    @Column(unique = true)
    protected String email;
    @Column(unique = true)
    protected String phone;

    @NotNull(message = "First name can not be empty")
    @Column(name = "first_name")
    protected String firstName;

    @NotNull(message = "Last name can not be empty")
    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "customer_code")
    protected String customerCode;

    @OneToOne(orphanRemoval = true,cascade=CascadeType.ALL)
    @JoinColumn(name = "funding_account_id", referencedColumnName = "id")
    protected JpaFundingAccount fundingAccount;
    protected String password;
    protected String address;
    protected String city;
    protected String state;
    protected String country;
    @Temporal(TemporalType.DATE)
    //@Past(message = "Date of birth must be a date in the past")
    @Column(name = "date_of_birth")
    protected Date dateOfBirth;
    protected String gender;
    @Column(name = "next_of_kin_name")
    protected String nextOfKinName;
    @Column(name = "next_of_kin_address")
    protected String nextOfKinAddress;
    @Column(name = "next_of_kin_phone")
    protected String NextOfKinPhone;
    @JsonIgnore
    @Column(name = "is_phone_verified")
    protected Boolean isPhoneVerified = false;
    @JsonIgnore
    @Column(name = "is_email_verified")
    protected Boolean isEmailVerified = false;
    @JsonIgnore
    @Column(name = "is_bvn_verified")
    protected Boolean isBvnVerified = false;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    protected JpaAgent agent;

    protected String pin;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    protected List<JpaPaymentMethod> jpaPaymentMethods = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_beneficiaries",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))
    protected List<JpaBeneficiary> beneficiaries = new ArrayList<>();

    @Column(name = "is_completed_tour")
    protected Boolean isCompletedTour = false;

    @Column(name = "is_disabled")
    protected Boolean isDisabled = false;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    protected List<JpaAccount> accounts = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<JpaRole> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_kyc_levels",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "kyc_level_id")
    )
    private Set<JpaKYCLevel> completedKYCLevels = new HashSet<>();

    
    public JpaUser() {
        super();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Boolean getIsCompletedTour() {
        return isCompletedTour;
    }

    public void setIsCompletedTour(Boolean isCompletedTour) {
        this.isCompletedTour = isCompletedTour;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public List<JpaPaymentMethod> getPaymentMethods() {
        return jpaPaymentMethods;
    }

    public void setPaymentMethods(List<JpaPaymentMethod> jpaPaymentMethods) {
        this.jpaPaymentMethods = jpaPaymentMethods;
    }

    public List<JpaBeneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<JpaBeneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public String getNextOfKinName() {
        return nextOfKinName;
    }

    public void setNextOfKinName(String nextOfKinName) {
        this.nextOfKinName = nextOfKinName;
    }

    public String getNextOfKinAddress() {
        return nextOfKinAddress;
    }

    public void setNextOfKinAddress(String nextOfKinAddress) {
        this.nextOfKinAddress = nextOfKinAddress;
    }

    public String getNextOfKinPhone() {
        return NextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        NextOfKinPhone = nextOfKinPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addPaymentMethod(JpaPaymentMethod method) {
        method.setOwner(this);
        jpaPaymentMethods.add(method);
    }

    public Boolean getIsBvnVerified() {
        return isBvnVerified;
    }

    public void setIsBvnVerified(Boolean isBvnVerified) {
        this.isBvnVerified = isBvnVerified;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public JpaFundingAccount getFundingAccount() {
        return fundingAccount;
    }

    public void setFundingAccount(JpaFundingAccount fundingAccount) {
        this.fundingAccount = fundingAccount;
    }

    public Boolean getPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getBvnVerified() {
        return isBvnVerified;
    }

    public void setBvnVerified(Boolean bvnVerified) {
        isBvnVerified = bvnVerified;
    }

    public List<JpaPaymentMethod> getJpaPaymentMethods() {
        return jpaPaymentMethods;
    }

    public void setJpaPaymentMethods(List<JpaPaymentMethod> jpaPaymentMethods) {
        this.jpaPaymentMethods = jpaPaymentMethods;
    }

    public Boolean getCompletedTour() {
        return isCompletedTour;
    }

    public void setCompletedTour(Boolean completedTour) {
        isCompletedTour = completedTour;
    }

    public JpaAgent getAgent() {
        return agent;
    }

    public void setAgent(JpaAgent agent) {
        this.agent = agent;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public List<JpaAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<JpaAccount> accounts) {
        this.accounts = accounts;
    }    
    
    public String getUsername(){
        return this.email;
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        return true;
    }
   
    public boolean isEnabled() {
        return !this.isDisabled;
    }

    public Set<JpaRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<JpaRole> roles) {
        this.roles = roles;
    }      
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<JpaPermission> permissions = new HashSet<>();
        this.roles.forEach(role -> {
            permissions.addAll(role.getPermissions()
                    .stream()
                    .collect(Collectors.toList()));
        });
        permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        });
        return authorities;
    }

    public Set<JpaKYCLevel> getCompletedKYCLevels() {
        return completedKYCLevels;
    }

    public void setCompletedKYCLevels(Set<JpaKYCLevel> completedKYCLevels) {
        this.completedKYCLevels = completedKYCLevels;
    }
    
    
}
