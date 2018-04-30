package com.lavreniuk.gymcounter.security;

import com.lavreniuk.gymcounter.domain.User;
import com.lavreniuk.gymcounter.security.utils.JwtUtils;
import com.lavreniuk.gymcounter.service.UserService.UserService;
import com.lavreniuk.gymcounter.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;

/**
 * @author taras
 * @date 15.04.18.
 */
@Component
public class CustomAuthentication {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    public CustomAuthentication(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    public String attemptAuthentication(String username, String password) throws AuthenticationException {
        User checkedUser = userService.getByUsername(username);
        if (checkedUser != null &&
                checkedUser.getPassword().equals(PasswordHashing.hashPassword(password))) {
            return jwtUtils.generateToken(checkedUser);
        } else {
            throw new AuthenticationException();
        }
    }

}
