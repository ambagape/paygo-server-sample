package com.gitittech.paygo.integrations.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewBillRequest {

    private String country;
    private String customer;
    private Long amount;
    private String recurrence;
    private String type;
    private String reference;

}
