package com.example.paysystem.exception.accountdetails;

public class AccountDetailsWithCurrentIdNotFound extends AccountDetailsException{
    public AccountDetailsWithCurrentIdNotFound() {
        super();
    }

    public AccountDetailsWithCurrentIdNotFound(String message) {
        super(message);
    }

    public AccountDetailsWithCurrentIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDetailsWithCurrentIdNotFound(Throwable cause) {
        super(cause);
    }
}
