package com.gitittech.paygo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitittech.paygo.commons.entities.BaseEntity;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kyc_levels")
public class JpaKYCLevel extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @NotNull(message = "Name cannot be empty")
    @Column(unique = true)
    protected String name;

    @ManyToMany
    @JoinTable(
            name = "kyc_level_requirements",
            joinColumns = @JoinColumn(name = "kyc_level_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id"))
    protected List<JpaRequirement> requirements = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "kyc_level_pivileges",
            joinColumns = @JoinColumn(name = "kyc_level_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    protected List<JpaPrivilege> privileges = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "kyc_level_dependencies",
            joinColumns = @JoinColumn(name = "kyc_level_id"),
            inverseJoinColumns = @JoinColumn(name = "dependant_kyc_level_id"))
    protected List<JpaKYCLevel> dependencies = new ArrayList<>();
  
    public JpaKYCLevel() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JpaRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<JpaRequirement> requirements) {
        this.requirements = requirements;
    }

    public List<JpaPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<JpaPrivilege> privileges) {
        this.privileges = privileges;
    }

    public List<JpaKYCLevel> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<JpaKYCLevel> dependencies) {
        this.dependencies = dependencies;
    }
        
    
    
}
