package com.lwcd.user.service.exception;

import com.lwcd.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFOundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFOundException ex){

        String message = ex.getMessage();
        ApiResponse response =ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
    return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }

}
