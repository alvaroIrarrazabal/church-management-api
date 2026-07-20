package com.irarrazabal.iglesiaapi.exceptions;

public class SamePasswordExceptions extends RuntimeException {
    public SamePasswordExceptions(String message) {
        super(message);
    }
}
