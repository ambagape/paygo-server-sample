package com.gitittech.paygo.user.dtos.payments;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountDebit extends PaymentMethod {
    @NotNull(message = "account number can't be empty")
    private String accountNo;
    @NotNull(message = "account name can't be empty")
    private String accountName;
    @NotNull(message = "bank code can't be empty")
    private String bankCode;
}
