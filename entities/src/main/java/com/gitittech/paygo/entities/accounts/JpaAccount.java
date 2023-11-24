package com.gitittech.paygo.entities.accounts;

import com.gitittech.paygo.commons.entities.BaseEntity;
import com.gitittech.paygo.entities.JpaUser;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class JpaAccount extends BaseEntity {

    private String name;
    private String note;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private JpaUser owner;
    
    @Column(name = "cached_balance")
    private BigDecimal cachedBalance;
    
    @Column(name = "cached_balance_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cachedBalanceDate;
}
