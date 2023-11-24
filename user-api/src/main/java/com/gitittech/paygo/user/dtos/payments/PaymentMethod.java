package com.gitittech.paygo.user.dtos.payments;

import com.gitittech.paygo.commons.dtos.BaseDTO;
import com.gitittech.paygo.user.dtos.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethod extends BaseDTO {

    protected User owner;

    protected String authorizationCode;

}
