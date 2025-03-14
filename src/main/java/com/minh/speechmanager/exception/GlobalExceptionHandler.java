package com.minh.speechmanager.exception;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", exception.getStatusCode().value());
        errorResponse.put("error", HttpStatus.valueOf(exception.getStatusCode().value()).getReasonPhrase());
        errorResponse.put("message", exception.getReason());

        return new ResponseEntity<>(errorResponse, exception.getStatusCode());
    }
}
