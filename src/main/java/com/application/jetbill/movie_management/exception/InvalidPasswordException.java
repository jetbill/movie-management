package com.application.jetbill.movie_management.exception;

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

    public String getPassword() {
        return password;
    }
    public String getPasswordRepeated() {
        return passwordRepeated;
    }
    public String getErrorDescription() {
        return errorDescription;
    }

    @Override
    public String getMessage() {
        return "InvalidPassword" + errorDescription;
    }
}
