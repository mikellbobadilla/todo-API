package com.mikellbobadilla.todo.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record PageNotFound(
        HttpStatus httpStatus,
        String message,
        Date timestamp,
        String path,
        String docs
) {
}
