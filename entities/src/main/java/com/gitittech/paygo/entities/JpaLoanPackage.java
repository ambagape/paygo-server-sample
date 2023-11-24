/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import com.gitittech.paygo.commons.entities.UnModifiableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ambag
 */
@Entity
@Table(name = "loan_packages")
@Getter
@Setter
public class JpaLoanPackage extends BaseEntity {

    private String name;
    @Column(name = "term_in_days")
    private Long termInDays;
    @Column(name = "number_of_repayments")
    private Long numberOfRepayments;
    @Column(name = "interest_rate")
    private Double interestRate;
}
