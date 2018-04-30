package com.lavreniuk.gymcounter.security;

import com.lavreniuk.gymcounter.domain.User;
import com.lavreniuk.gymcounter.security.domain.AuthenticationUser;
import com.lavreniuk.gymcounter.security.exception.JwtTokenMalformedException;
import com.lavreniuk.gymcounter.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author taras
 * @date 15.04.18.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username,
                                       UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        User parsedUser = jwtUtils.parseToken(token);

        if (parsedUser == null) {
            try {
                throw new JwtTokenMalformedException("JWT token is not valid");
            } catch (JwtTokenMalformedException e) {
                e.printStackTrace();
            }
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

        return new AuthenticationUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
    }


}
