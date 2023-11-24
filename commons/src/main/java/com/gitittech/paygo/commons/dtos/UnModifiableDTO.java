package com.gitittech.paygo.commons.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UnModifiableDTO {

    protected Long id;
    protected Date created = new Date();
    protected String createdBy;

}
