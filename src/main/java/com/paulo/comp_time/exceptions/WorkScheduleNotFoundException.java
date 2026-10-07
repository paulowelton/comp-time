package com.paulo.comp_time.exceptions;

public class WorkScheduleNotFoundException extends RuntimeException {
    public WorkScheduleNotFoundException(String message) {
        super(message);
    }
}
