package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "Could Not Save User")
public class CouldNotSaveException extends RuntimeException {
    public CouldNotSaveException(String id) {
        super("Could not save user " + id);
    }
    @ExceptionHandler(CouldNotSaveException.class)
    public void handleException(CouldNotSaveException  e , HttpServletResponse response) {
        try {
            response.sendError(HttpStatus.BAD_GATEWAY.value(), e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
