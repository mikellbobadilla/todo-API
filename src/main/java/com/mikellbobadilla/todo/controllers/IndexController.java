package com.mikellbobadilla.todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class IndexController {

    @GetMapping
    public ResponseEntity<Map<String, String>> indexPage(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Pagina Principal");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
