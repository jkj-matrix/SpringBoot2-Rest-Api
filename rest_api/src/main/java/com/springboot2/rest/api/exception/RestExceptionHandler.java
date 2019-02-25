package com.springboot2.rest.api.exception;

import com.springboot2.rest.api.dto.Error;
import com.springboot2.rest.api.dto.PersonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public PersonResponse handleCustomException(Exception exception){
        PersonResponse response = new PersonResponse();
        response.setError(new Error("[Unexpected exception was detected] - " + exception));
        return response;
    }
}
