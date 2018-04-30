package com.lavreniuk.gymcounter.responses;

import org.springframework.http.HttpStatus;

/**
 * @author taras
 * @date 22.04.18.
 */
public class ErrorResponse extends Response  {

    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
