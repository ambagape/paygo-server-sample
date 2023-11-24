package com.gitittech.paygo.user.dtos.payments;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebitCard extends PaymentMethod {
    private String bin;
    private String last4Digit;
    private String issuer;
    private String type;
    private String country;
    private String expiry;
}
