package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Could not find User " + id);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public void handleException(UserNotFoundException  e , HttpServletResponse response) {
        try {
            response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
