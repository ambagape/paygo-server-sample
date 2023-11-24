package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class JpaRole extends BaseEntity {
    
    @Column(unique = true)
    private String name;  
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<JpaPermission> permissions = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JpaPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<JpaPermission> permissions) {
        this.permissions = permissions;
    }       
}
