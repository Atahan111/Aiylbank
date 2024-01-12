package com.example.demo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class SimpleResponse {
    HttpStatus  httpStatus;
    String message;
}
