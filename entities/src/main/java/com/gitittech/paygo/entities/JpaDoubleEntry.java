/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import com.gitittech.paygo.entities.enums.EntryStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ambag
 */
@Entity
@Getter
@Setter
@Table(name = "double_entries")
public class JpaDoubleEntry extends BaseEntity {

    @NotNull
    private Long amount;
    
    @Column(name = "transaction_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactTime;

    @OneToMany(mappedBy = "doubleEntry")
    private List<JpaTransaction> transactions = new java.util.ArrayList<>();

    @NotNull
    @Column(name = "transaction_type")
    private String transactionType;

    private String note;
    
    private String reference;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUser user;

    @NotNull
    private EntryStatus status;
    
    public void addTransactions(JpaTransaction creditTransaction, JpaTransaction debitTransaction){
        this.transactions.add(creditTransaction);
        this.transactions.add(debitTransaction);
    }
}
