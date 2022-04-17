package com.example.paysystem.exception.user;

import com.example.paysystem.exception.user.UserException;

public class UserWithCurrentIdNotFound extends UserException {
    public UserWithCurrentIdNotFound() {
        super();
    }

    public UserWithCurrentIdNotFound(String message) {
        super(message);
    }

    public UserWithCurrentIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithCurrentIdNotFound(Throwable cause) {
        super(cause);
    }
}
