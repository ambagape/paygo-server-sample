package com.gitittech.paygo.commons.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


import java.util.Date;

@MappedSuperclass
abstract public class BaseEntity extends UnModifiableEntity {

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modified = new Date();

    @JsonIgnore
    @Column(name = "modified_by")
    protected String modifiedBy;

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
