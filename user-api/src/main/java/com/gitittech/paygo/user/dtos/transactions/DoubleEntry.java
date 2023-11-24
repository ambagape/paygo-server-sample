/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.user.dtos.transactions;

import com.gitittech.paygo.commons.dtos.BaseDTO;
import com.gitittech.paygo.entities.JpaTransaction;
import com.gitittech.paygo.entities.enums.EntryStatus;
import com.gitittech.paygo.user.dtos.User;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ambag
 */
@Data
@NoArgsConstructor
public class DoubleEntry extends BaseDTO {

    private BigDecimal amount;
   
    private Date transactTime;

    private List<JpaTransaction> transactions;
    
    private String transactionType;

    private String note;

    private String reference;

    private EntryStatus status;
    
    private User user;
}
