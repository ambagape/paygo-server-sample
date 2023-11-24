package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "payment_methods")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class JpaPaymentMethod extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    protected JpaUser owner;

    @Column(name = "authorization_code")
    protected String authorizationCode;

    public JpaUser getOwner() {
        return owner;
    }

    public void setOwner(JpaUser owner) {
        this.owner = owner;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }


}
