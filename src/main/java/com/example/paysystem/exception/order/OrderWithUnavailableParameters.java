package com.example.paysystem.exception.order;

public class OrderWithUnavailableParameters extends OrderException {
    public OrderWithUnavailableParameters() {
        super();
    }

    public OrderWithUnavailableParameters(String message) {
        super(message);
    }

    public OrderWithUnavailableParameters(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderWithUnavailableParameters(Throwable cause) {
        super(cause);
    }
}
