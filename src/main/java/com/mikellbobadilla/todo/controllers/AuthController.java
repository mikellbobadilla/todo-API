package com.mikellbobadilla.todo.controllers;

import com.mikellbobadilla.todo.dto.AuthRecord;
import com.mikellbobadilla.todo.dto.JwtRecord;
import com.mikellbobadilla.todo.dto.RegisterRecord;
import com.mikellbobadilla.todo.dto.UserRecord;
import com.mikellbobadilla.todo.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtRecord> authenticate(@RequestBody AuthRecord authRecord){
        return new ResponseEntity<>(authService.authenticate(authRecord),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRecord> register(@RequestBody UserRecord userRecord){
        return new ResponseEntity<>(authService.register(userRecord), HttpStatus.CREATED);
    }
}
