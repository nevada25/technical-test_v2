package com.ibk.spring.cloud.msvc.msvcpayments.exceptions;


import com.ibk.spring.cloud.msvc.msvcpayments.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        String message = resourceNotFoundException.getMessage();

        ApiResponse response = new ApiResponse().builder()
                .message(message)
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
