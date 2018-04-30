package com.lavreniuk.gymcounter.enums;

/**
 * @author taras
 * @date 21.04.18.
 */
public enum ResponseMessages {

    UserWithSuchEMailAlreadyExistError("User with such email already exist."),
    UserWithSuchUsernameAlreadyExistError("User with such username already exist."),
    AuthenticationFailed("Authentication failed. Incorrect credentials."),
    UserIdNotFound("There is no user id in request. Please specify user id.");

    private String message;

    ResponseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}