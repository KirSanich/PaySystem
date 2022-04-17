package com.example.paysystem.exception.accountdetails;

public abstract class AccountDetailsException extends RuntimeException{
    public AccountDetailsException() {
        super();
    }

    public AccountDetailsException(String message) {
        super(message);
    }

    public AccountDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDetailsException(Throwable cause) {
        super(cause);
    }
}
