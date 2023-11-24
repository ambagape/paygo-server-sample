package com.gitittech.paygo.integrations.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNewBillReq{

    private NewBillRequest billDetails;
    private Boolean recurring;
    private String PIN;
    private Long intervalInDays;

    public EditableTransaction toEditableTransaction() {
        if (billDetails == null)
            throw new IllegalArgumentException("billDetails was not supplied");
        EditableTransaction et = new EditableTransaction();
        et.setAmount(billDetails.getAmount());
        et.setPIN(PIN);
        et.setRecipientIdentifier(billDetails.getType());
        et.setRemark("Bill payment " + billDetails.getReference());
        et.setBillDetails(billDetails);

        return et;
    }
}
