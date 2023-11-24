package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "requirements")
public class JpaRequirement extends BaseEntity implements Serializable {
    
    private String name;
    
    private String type;
    
    private boolean requiresApproval;
    
    public JpaRequirement() {

    }   

    public boolean isRequiresApproval() {
        return requiresApproval;
    }

    public void setRequiresApproval(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
        
}
