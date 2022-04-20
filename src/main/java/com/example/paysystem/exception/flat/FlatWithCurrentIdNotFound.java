package com.example.paysystem.exception.flat;

public class FlatWithCurrentIdNotFound extends FlatException{
    public FlatWithCurrentIdNotFound() {
        super();
    }

    public FlatWithCurrentIdNotFound(String message) {
        super(message);
    }

    public FlatWithCurrentIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public FlatWithCurrentIdNotFound(Throwable cause) {
        super(cause);
    }
}
