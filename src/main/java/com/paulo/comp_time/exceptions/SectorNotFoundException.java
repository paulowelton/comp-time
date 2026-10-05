package com.paulo.comp_time.exceptions;

public class SectorNotFoundException extends RuntimeException{
    public SectorNotFoundException(String message) {
        super(message);
    }
}
