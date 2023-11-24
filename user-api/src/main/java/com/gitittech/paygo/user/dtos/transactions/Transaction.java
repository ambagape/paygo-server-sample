package com.gitittech.paygo.user.dtos.transactions;

import com.gitittech.paygo.user.dtos.beneficiaries.Account;
import com.gitittech.paygo.commons.dtos.BaseDTO;
import com.gitittech.paygo.entities.enums.EntryStatus;
import com.gitittech.paygo.entities.enums.TransactionType;
import com.gitittech.paygo.user.dtos.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
public class Transaction extends BaseDTO {
    private User user;
    @NotNull
    private TransactionType transactionType;
    @NotNull
    private Long amount;

    private Date transactTime;
    @NotNull
    private String remark;

    private String reference;

    private Account recipient;

    private String PIN;

    @NotNull
    private EntryStatus status = EntryStatus.DRAFT;

}
