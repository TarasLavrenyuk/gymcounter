package com.lavreniuk.gymcounter.security.utils;

import com.lavreniuk.gymcounter.security.domain.AuthenticationUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author taras
 * @date 28.04.18.
 */
public class AuthenticationUtils {

    public static AuthenticationUser getCurrentAuthenticationUser() {
        return (AuthenticationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getCurrentUserId() {
        return getCurrentAuthenticationUser().getId();
    }

}
