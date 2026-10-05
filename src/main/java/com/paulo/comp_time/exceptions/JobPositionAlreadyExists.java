package com.paulo.comp_time.exceptions;

public class JobPositionAlreadyExists extends RuntimeException {
    public JobPositionAlreadyExists(String message) {
        super(message);
    }
}
