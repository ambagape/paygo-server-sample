package com.gitittech.paygo.integrations.dtos;

import com.gitittech.paygo.commons.dtos.BaseDTO;
import com.gitittech.paygo.entities.enums.TransactionType;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EditableTransaction extends BaseDTO {

    private String recipientIdentifier;

    private String remark;

    private Long amount;

    private Long charge;

    private String methodId;

    private String PIN;

    private String transactionPIN;

    private String email;

    private String phone;

    private String lastName;

    private String firstName;

    private Boolean saveBeneficiary = false;

    private NewBillRequest billDetails;

    private TransactionType type;

    private String customerName;

    private String customerPhone;

    private String reference;
      
}
