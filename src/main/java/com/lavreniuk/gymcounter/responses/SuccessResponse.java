package com.lavreniuk.gymcounter.responses;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author taras
 * @date 22.04.18.
 */
public class SuccessResponse<T> extends Response {

    private T object;
    private List<T> objects;

    public SuccessResponse(HttpStatus status) {
        super(status);
    }

    public SuccessResponse(HttpStatus status, T object) {
        super(status);
        this.object = object;
    }

    public SuccessResponse(HttpStatus status, List<T> objects) {
        super(status);
        this.objects = objects;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
