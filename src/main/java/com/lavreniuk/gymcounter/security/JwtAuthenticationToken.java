package com.lavreniuk.gymcounter.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author taras
 * @date 15.04.18.
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
