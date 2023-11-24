package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;
import javax.persistence.*;

import java.util.List;

@Table(name = "recipients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class JpaBeneficiary extends BaseEntity {

    @JsonIgnore
    @ManyToMany(mappedBy = "beneficiaries")
    protected List<JpaUser> benefactors;

    public List<JpaUser> getBenefactors() {
        return benefactors;
    }

    public void setBenefactors(List<JpaUser> owner) {
        this.benefactors = owner;
    }

}
