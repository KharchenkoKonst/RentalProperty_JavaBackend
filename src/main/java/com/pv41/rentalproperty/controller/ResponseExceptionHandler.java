package com.pv41.rentalproperty.controller;

import com.pv41.rentalproperty.model.Response;
import com.pv41.rentalproperty.security.jwt.JwtAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    protected ResponseEntity<Response> handleJwtException(JwtAuthenticationException e) {
        Response response = new Response();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}