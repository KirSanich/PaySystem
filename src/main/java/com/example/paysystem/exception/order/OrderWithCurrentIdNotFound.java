package com.example.paysystem.exception.order;

public class OrderWithCurrentIdNotFound extends RuntimeException{
    public OrderWithCurrentIdNotFound() {
        super();
    }

    public OrderWithCurrentIdNotFound(String message) {
        super(message);
    }

    public OrderWithCurrentIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderWithCurrentIdNotFound(Throwable cause) {
        super(cause);
    }
}
