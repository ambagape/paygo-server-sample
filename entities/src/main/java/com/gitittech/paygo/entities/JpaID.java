package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;

import javax.persistence.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.validation.constraints.Future;

@Entity
@Table(name = "ids")
public class JpaID extends BaseEntity {
    @NotNull
    private String number;
    private String type;
    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "issue_date")
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    @Future
    @Column(name = "exp_date")
    private Date expDate;

    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
