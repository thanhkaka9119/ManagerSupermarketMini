package com.vnpt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage TodoException(NotFoundException ex, WebRequest request) {
        System.out.println(ex.getLocalizedMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerDuplicateRecordException(DuplicateRecordException ex, WebRequest request) {
        System.out.println(ex.getLocalizedMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    // Xử lý tất cả các exception chưa được khai báo
    @ExceptionHandler({Exception.class,ServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handlerException(Exception ex, WebRequest req) {
        System.out.println(ex.getLocalizedMessage());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

}
