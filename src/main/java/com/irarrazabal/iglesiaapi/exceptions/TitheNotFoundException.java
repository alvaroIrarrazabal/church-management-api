package com.irarrazabal.iglesiaapi.exceptions;

public class TitheNotFoundException extends RuntimeException {
    public TitheNotFoundException(String message) {
        super(message);
    }
}
