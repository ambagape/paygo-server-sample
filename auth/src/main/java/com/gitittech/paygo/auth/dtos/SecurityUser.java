package com.gitittech.paygo.auth.dtos;

import com.gitittech.paygo.entities.JpaPermission;
import com.gitittech.paygo.entities.JpaUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ambag
 */
public class SecurityUser extends JpaUser implements UserDetails{

    private String username;  
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    public SecurityUser(String username, String password, boolean isEnabled,  Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.isDisabled = isEnabled;
        this.authorities = authorities;
    }
     
    public SecurityUser(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isDisabled = user.isEnabled();
        this.authorities = user.getAuthorities();
    }
    
    public SecurityUser(JpaUser user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isDisabled = user.getIsDisabled();

        Set<JpaPermission> permissions = new HashSet<>();

        user.getRoles().forEach(role -> {
            permissions.addAll(role.getPermissions()
                    .stream()
                    .collect(Collectors.toList()));
        });
        permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        });
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDisabled;
    }  

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
