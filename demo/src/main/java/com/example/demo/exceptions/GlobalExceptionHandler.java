package com.example.demo.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) throws JsonProcessingException {
        return new ResponseEntity<>(new ErrorResponse(
                ex.getMessage(),
                ex.getStackTrace(),
                ex.getObjectErrors()
        ), HttpStatus.BAD_REQUEST);
    }
    private static class ErrorResponse {
        private final String messageError;
        private final String stack;
        private final Object errors;

        public ErrorResponse(String messageError, StackTraceElement[] stackTrace, Object errors) throws JsonProcessingException {
            this.messageError = messageError;
            this.stack = new ObjectMapper().writeValueAsString(stackTrace);
            this.errors = errors;
        }

        public String getMessageError() {
            return messageError;
        }

        public String getStack() {
            return stack;
        }

        public Object getErrors() {
            return errors;
        }
    }
}
