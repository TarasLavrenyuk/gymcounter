package com.lavreniuk.gymcounter.responses;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author taras
 * @date 22.04.18.
 */
public class Response implements Serializable {

    private final Integer code;
    private final String phrase;


    public Response(HttpStatus status) {
        this.code = status.value();
        this.phrase = status.getReasonPhrase();
    }

    public Integer getCode() {
        return code;
    }

    public String getPhrase() {
        return phrase;
    }
}
