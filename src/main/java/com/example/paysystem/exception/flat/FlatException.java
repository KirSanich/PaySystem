package com.example.paysystem.exception.flat;

public abstract class FlatException extends RuntimeException {
    public FlatException() {
        super();
    }

    public FlatException(String message) {
        super(message);
    }

    public FlatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlatException(Throwable cause) {
        super(cause);
    }
}
