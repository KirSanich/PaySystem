package com.example.paysystem.exception;

public class UserWithUsernameNotFound extends UserException{
    public UserWithUsernameNotFound() {
        super();
    }

    public UserWithUsernameNotFound(String message) {
        super(message);
    }

    public UserWithUsernameNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithUsernameNotFound(Throwable cause) {
        super(cause);
    }
}
