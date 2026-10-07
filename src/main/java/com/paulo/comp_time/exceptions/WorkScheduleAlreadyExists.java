package com.paulo.comp_time.exceptions;

public class WorkScheduleAlreadyExists extends RuntimeException {
    public WorkScheduleAlreadyExists(String message) {
        super(message);
    }
}
