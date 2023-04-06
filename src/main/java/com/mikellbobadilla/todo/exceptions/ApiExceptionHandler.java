package com.mikellbobadilla.todo.exceptions;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<Object, Object>> duplicateEntry(SQLIntegrityConstraintViolationException exc){

        return buildResponseEntity("This user already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badCredentialsException(AuthenticationException exc){
        return buildResponseEntity(exc, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> notFoundException(HttpClientErrorException.NotFound exc){
//        return buildResponseEntity(exc, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(value = NoHandlerFoundException.class)
//    public ResponseEntity<ErrorResponse> exceptionGeneric(NoHandlerFoundException exc){
//        return buildResponseEntity(exc, HttpStatus.NOT_FOUND);
//    }

    private ResponseEntity<Map<Object, Object>> buildResponseEntity(String message, HttpStatus httpStatus){
        Map<Object, Object> map = new HashMap<>();
        map.put("httpStatus", httpStatus);
        map.put("message", message);
        map.put("timestamp", new Date());
        return new ResponseEntity<>(map, httpStatus);
    }
    private ResponseEntity<ErrorResponse> buildResponseEntity(Exception exp, HttpStatus httpStatus){
        ErrorResponse error = new ErrorResponse(httpStatus, exp.getMessage(), new Date());
        return new ResponseEntity<>(error, httpStatus);
    }
}
