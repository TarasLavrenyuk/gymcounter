package com.lavreniuk.gymcounter.controllers;

import com.lavreniuk.gymcounter.security.domain.AuthenticationUser;
import com.lavreniuk.gymcounter.security.utils.AuthenticationUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author taras
 * @date 15.04.18.
 */
public class BaseController {

    protected Long getCurrentUserId() {
        return AuthenticationUtils.getCurrentUserId();
    }

    protected AuthenticationUser getCurrentAuthenticationUser() {
        return AuthenticationUtils.getCurrentAuthenticationUser();
    }
}
