package com.gitittech.paygo.user.dtos.beneficiaries;

import com.gitittech.paygo.commons.dtos.BaseDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account extends BaseDTO {
    private String name;
    private String note;
    private BigDecimal cachedBalance;    
    private Date cachedBalanceDate;
}
