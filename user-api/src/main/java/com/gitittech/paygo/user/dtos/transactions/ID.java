package com.gitittech.paygo.user.dtos.transactions;

import com.gitittech.paygo.commons.dtos.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
public class ID extends BaseDTO {
    @NotNull
    private String number;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    private Date issueDate;
    @Future
    private Date expDate;

    private String status;


}
