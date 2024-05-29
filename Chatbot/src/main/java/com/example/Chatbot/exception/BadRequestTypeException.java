package com.example.Chatbot.exception;

public class BadRequestTypeException extends RuntimeException {
    public BadRequestTypeException(String message) {
        super(message);
    }
}
