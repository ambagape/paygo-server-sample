package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "agents")
public class JpaAgent extends BaseEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "identification_id", referencedColumnName = "id")
    @Cascade(CascadeType.ALL)
    private JpaID identification;
    @NotNull
    @Column(name = "business_name")
    private String businessName;
    @NotNull
    @Column(name = "business_address")
    private String businessAddress;
    @NotNull
    @Column(name = "utility_bill")
    private String utilityBill;
    @NotNull
    private String description;
    @JsonIgnore
    @Column(name = "is_approved")
    private Boolean isApproved = false;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;

    @OneToOne(mappedBy = "agent")
    private JpaUser owner;
     
    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public JpaID getIdentification() {
        return identification;
    }

    public void setIdentification(JpaID identification) {
        this.identification = identification;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public JpaUser getOwner() {
        return owner;
    }

    public void setOwner(JpaUser owner) {
        this.owner = owner;
    }
    
    
}
