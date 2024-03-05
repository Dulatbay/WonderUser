package kz.wonder.wonderuser.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }
}
