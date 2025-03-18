package com.application.jetbill.movie_management.exception;

import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException{

    private final String password;
    private final String passwordRepeated;
    private final String errorDescription;

    public InvalidPasswordException(String password, String passwordRepeated, String errorDescription) {
        this.password = password;
        this.passwordRepeated = passwordRepeated;
        this.errorDescription = errorDescription;
    }

    public InvalidPasswordException(String password, String errorDescription) {
        this.password = password;
        this.passwordRepeated =  password;
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        return "InvalidPassword" + errorDescription;
    }
}
