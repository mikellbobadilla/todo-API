package com.mikellbobadilla.todo.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateEntry(SQLIntegrityConstraintViolationException exc){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "This account already exists",
                new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badCredentialsException(AuthenticationException exc){
        return buildResponseEntity(exc, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> pageNotFoundException(PageNotFoundException exc){
        return buildResponseEntity(exc, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> jwtException(ExpiredJwtException exc){
        return buildResponseEntity(exc, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(Exception exp, HttpStatus httpStatus){
        ErrorResponse error = new ErrorResponse(httpStatus, exp.getMessage(), new Date());
        return new ResponseEntity<>(error, httpStatus);
    }
}
