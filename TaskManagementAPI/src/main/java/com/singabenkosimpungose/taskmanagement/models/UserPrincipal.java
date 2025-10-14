package com.singabenkosimpungose.taskmanagement.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * Implements UserDetails that represents authenticated user within the spring security context
 * 
 * The class acts as a bridge between the applications User entity and Spring Secity's authentication mechanism.
 * It adapts user information such as username, password, roles into a format that Spring Security
 * can use during authentication and authorization processes.
 * 
 * Spring security uses this class to:
 * Retrive user credentials and authoriries during login
 * Populate the SecurityContext after a successful authentication
 * Provide user details throughout the session via SecurityContextHolder.getContext().getAuthentication().getPrincipal()
 */

public class UserPrincipal implements UserDetails{

    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return true;
    }


}
