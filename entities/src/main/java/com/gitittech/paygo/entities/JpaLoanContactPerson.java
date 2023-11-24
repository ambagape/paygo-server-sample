/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ambag
 */
@Entity
@Table(name = "loan_contact_persons")
@Getter
@Setter
public class JpaLoanContactPerson extends BaseEntity {

    private String name;
    private String phone;
    private String relationship;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private JpaLoan loan;
}
