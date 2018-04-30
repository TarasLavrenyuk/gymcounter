package com.lavreniuk.gymcounter.security.exception;

import javax.naming.AuthenticationException;

/**
 * @author taras
 * @date 15.04.18.
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }

}
