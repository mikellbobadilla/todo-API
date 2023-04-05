package com.mikellbobadilla.todo.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private Date timestamp;

}
