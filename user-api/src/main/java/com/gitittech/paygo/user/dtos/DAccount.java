package com.gitittech.paygo.user.dtos;

import com.gitittech.paygo.commons.dtos.UnModifiableDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DAccount extends UnModifiableDTO {
    @NotNull(message = "account number can't be empty")
    private String accountNo;
    @NotNull(message = "account name can't be empty")
    private String accountName;
    @NotNull(message = "bank code can't be empty")
    private String bankCode;
    @NotNull(message = "currency can't be empty")
    private String currency;
    @NotNull(message = "bank name can't be empty")
    private String bankName;

}
