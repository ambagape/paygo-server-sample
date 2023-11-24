
package com.gitittech.paygo.auth;

import com.gitittech.paygo.entities.JpaPermission;
import com.gitittech.paygo.auth.enums.Permissions;
import com.gitittech.paygo.commons.seeders.ISeeder;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gitittech.paygo.auth.repositories.IPermissionRepository;

/**
 *
 * @author ambag
 */
@Service
public class PermissionSeeder implements ISeeder{
    final private IPermissionRepository repository;

    @Autowired
    public PermissionSeeder(IPermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        final var permissions = new ArrayList<JpaPermission>();
        for(final var permissionEnum : Permissions.values()){
            final var permission = new JpaPermission();
            permission.setName(permissionEnum.toString());
            permissions.add(permission);            
        }        
        repository.saveAll(permissions);
    }

    @Override
    public void runDemo() {        
    }
    
}
