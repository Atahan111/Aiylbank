package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String s) {
        super(s);
    }

}
