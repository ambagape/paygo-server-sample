/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ambag
 */
@Entity
@Table(name = "loans")
@Getter
@Setter
class JpaLoan extends BaseEntity {

    private Long principal;

    @Column(name = "interest_paid")
    private Long interestPaid;
    @Column(name = "payment_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "approval_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date approvalDate;
    @Column(name = "disbursement_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date disbursementDate;

    @Column(name = "application_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date applicationDate;

    @Column(name = "due_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dueDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private JpaUser customer;
    @ManyToOne
    @JoinColumn(name = "loan_package_id")
    private JpaLoanPackage loanPackage;
    @OneToMany(mappedBy = "loan")
    private Collection<JpaLoanContactPerson> contactPersons;
    @OneToMany(mappedBy = "loan")
    private Collection<JpaLoanPayment> paymentHistory;
}
