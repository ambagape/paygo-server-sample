package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class JpaPermission extends BaseEntity {
    @Column(unique = true)
    private String name;    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
