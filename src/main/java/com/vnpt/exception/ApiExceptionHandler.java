package com.vnpt.exception;

import com.vnpt.product.ProductController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage TodoException(NotFoundException ex,WebRequest request){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

}
