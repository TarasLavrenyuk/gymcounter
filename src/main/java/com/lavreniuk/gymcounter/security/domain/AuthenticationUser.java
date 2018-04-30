package com.lavreniuk.gymcounter.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author taras
 * @date 15.04.18.
 */
public class AuthenticationUser implements UserDetails {

    private Long id;
    private final String username;
    private String token;
    private String password;
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;


    public AuthenticationUser(Long id, String username, String token, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
