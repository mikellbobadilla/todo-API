package com.mikellbobadilla.todo.controllers;

import com.mikellbobadilla.todo.dto.PageNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class NotFoundController {
    @RequestMapping("*")
    public ResponseEntity<PageNotFound> notFoundPage(HttpServletRequest request){
        PageNotFound notFound = new PageNotFound(
            HttpStatus.NOT_FOUND,
            "This page does not exists",
            new Date(),
            request.getRequestURI(),
            "URL de swagger page"
        );
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }
}
