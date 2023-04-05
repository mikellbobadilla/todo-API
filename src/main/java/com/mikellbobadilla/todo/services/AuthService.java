package com.mikellbobadilla.todo.services;

import com.mikellbobadilla.todo.dto.AuthRecord;
import com.mikellbobadilla.todo.dto.JwtRecord;
import com.mikellbobadilla.todo.dto.RegisterRecord;
import com.mikellbobadilla.todo.dto.UserRecord;
import com.mikellbobadilla.todo.entities.Role;
import com.mikellbobadilla.todo.entities.UserEntity;
import com.mikellbobadilla.todo.jwt.JwtService;
import com.mikellbobadilla.todo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterRecord register(UserRecord register){
        UserEntity user = UserEntity.builder()
                .name(register.name())
                .username(register.username())
                .password(passwordEncoder.encode(register.password()))
                .role(Role.USER)
                .build();

        UserEntity userSaved = userRepository.save(user);
        String token = jwtService.createToken(userSaved);
        return new RegisterRecord("Welcome " + userSaved.getName(), token);
    }

    public JwtRecord authenticate(AuthRecord credentials){
        // Spring validate credentials what received
        // That was implemente as a Bean in the ApplicationConfig.class
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.username(),
                credentials.password()
            )
        );
        UserEntity user = userRepository.findByUsername(credentials.username())
                .orElseThrow(()-> new UsernameNotFoundException("Bad Credentials"));

        String token = jwtService.createToken(user);
        return new JwtRecord(token);
    }
}
