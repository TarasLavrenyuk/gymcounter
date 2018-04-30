package com.lavreniuk.gymcounter.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author taras
 * @date 15.04.18.
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(String msg) {
        super(msg);
    }

}
