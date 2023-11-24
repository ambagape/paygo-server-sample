package com.gitittech.paygo.user.dtos.payments;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gitittech.paygo.commons.MoneySerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Wallet extends PaymentMethod {

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal balance = new BigDecimal(0);

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal ledgerBalance = new BigDecimal(0);

}
