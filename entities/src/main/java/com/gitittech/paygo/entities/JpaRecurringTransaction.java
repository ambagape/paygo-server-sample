package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "recurring_transactions")
public class JpaRecurringTransaction extends BaseEntity {
    @OneToOne
    private JpaTransaction jpaTransaction;
    @Column(name = "interval_between_occurence")
    private Integer intervalBetweenOccurrence = 1;
    @Column(name = "interval_type")
    private String intervalType = "H";
    private String status;

    public JpaTransaction getTransaction() {
        return jpaTransaction;
    }

    public void setTransaction(JpaTransaction jpaTransaction) {
        this.jpaTransaction = jpaTransaction;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIntervalBetweenOccurrence() {
        return intervalBetweenOccurrence;
    }

    public void setIntervalBetweenOccurrence(Integer intervalBetweenOccurrence) {
        this.intervalBetweenOccurrence = intervalBetweenOccurrence;
    }


}
