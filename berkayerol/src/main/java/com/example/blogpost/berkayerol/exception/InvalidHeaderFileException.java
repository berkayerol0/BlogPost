package com.example.blogpost.berkayerol.exception;

import lombok.Data;

@Data
public class InvalidHeaderFileException extends RuntimeException {

    private static final String serialversionUID = "1L";

    private String message;

    public InvalidHeaderFileException(String message) {
        this.setMessage(message);
    }
}
