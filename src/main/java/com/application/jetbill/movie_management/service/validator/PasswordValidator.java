package com.application.jetbill.movie_management.service.validator;

import com.application.jetbill.movie_management.exception.InvalidPasswordException;
import org.springframework.util.StringUtils;

public class PasswordValidator {
    public static void validatePassword(String password, String passwordRepeated) {
        if(!StringUtils.hasText(password) || !StringUtils.hasText(passwordRepeated)) {
            throw new IllegalArgumentException("Password must contain data");
        }
        if(!password.equals(passwordRepeated)) {
            throw new InvalidPasswordException(password, passwordRepeated, "Password does not match");
        }
        if(!containsNumber(password)){
            throw new InvalidPasswordException(password, "Password must contain at last one number");
        }
        if(!containsUppercase(password)){
            throw new InvalidPasswordException(password, "Password must contain at least one uppercase letter");

        }
        if(!containsLowerCase(password)){
            throw new InvalidPasswordException(password, "Password must contain at least one lowercase letter");
        }
        if(!containsSpacialCharacter(password)){
            throw new InvalidPasswordException(password, "Password must contain at least one special character");
        }
    }

    private static boolean containsNumber(String password) {
        return password.matches(".*\\d.*");
    }
    private static boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }
    private static boolean containsLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }
    private static boolean containsSpacialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*");
    }
}
