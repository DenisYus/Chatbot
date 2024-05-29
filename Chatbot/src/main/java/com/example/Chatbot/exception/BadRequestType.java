package com.example.Chatbot.exception;

public class BadRequestType extends RuntimeException{
    public BadRequestType(String message){
        super(message);
    }
}
