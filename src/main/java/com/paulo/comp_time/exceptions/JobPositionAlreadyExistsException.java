package com.paulo.comp_time.exceptions;

public class JobPositionAlreadyExistsException extends RuntimeException {
    public JobPositionAlreadyExistsException(String message) {
        super(message);
    }
}
