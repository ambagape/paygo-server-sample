package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "settings")
public class JpaSetting extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
