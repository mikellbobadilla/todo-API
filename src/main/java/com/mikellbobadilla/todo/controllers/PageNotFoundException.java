package com.mikellbobadilla.todo.controllers;

import com.mikellbobadilla.todo.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PageNotFoundException {

    @RequestMapping("*")
    public ResponseEntity<ErrorResponse> notFound(){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "Page Not Found", new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
