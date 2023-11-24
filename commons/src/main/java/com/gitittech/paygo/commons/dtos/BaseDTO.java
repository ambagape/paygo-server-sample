package com.gitittech.paygo.commons.dtos;

import com.gitittech.paygo.commons.entities.UnModifiableEntity;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO extends UnModifiableEntity {

    protected Date modified;

    protected String modifiedBy;

}
