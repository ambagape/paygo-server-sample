package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client_provileges")
public class JpaPrivilege extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @NotNull(message = "Name cannot be empty")
    @Column(unique = true)
    protected String name; 
   
    public JpaPrivilege() {
        super();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
