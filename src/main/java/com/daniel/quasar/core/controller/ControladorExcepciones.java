package com.daniel.quasar.core.controller;

import com.daniel.quasar.core.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorExcepciones {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(RuntimeException.class)
    public ResponseExceptionDTO handleDataValidationException(RuntimeException exception){
        return ResponseExceptionDTO.builder().responseCode(404).mensaje(exception.getMessage()).exception(exception.getClass().getSimpleName()).build();
    }
}
