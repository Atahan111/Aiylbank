package com.example.demo.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String s) {
        super(s);
    }
}
