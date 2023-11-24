package com.gitittech.paygo.entities;
import javax.persistence.*;

@Entity
public class JpaPaygoBeneficiary extends JpaBeneficiary {

    public JpaPaygoBeneficiary() {
    }
    
    public JpaPaygoBeneficiary(String id) {
        this.id = id;
    }
}
