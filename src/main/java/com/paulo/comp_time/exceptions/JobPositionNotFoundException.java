package com.paulo.comp_time.exceptions;

public class JobPositionNotFoundException extends RuntimeException {
    public JobPositionNotFoundException(String message) {
        super(message);
    }
}
