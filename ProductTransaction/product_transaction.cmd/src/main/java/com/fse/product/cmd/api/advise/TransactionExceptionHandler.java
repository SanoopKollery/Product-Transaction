package com.fse.product.cmd.api.advise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgumentException(MethodArgumentNotValidException ex)
    {
        Map<String, String> errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return  errorMap;
    }

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionNotFoundException.class)
    public Map<String, String> handleTransactionNotFound(TransactionNotFoundException ex)
    {
        Map<String, String> errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BidDateExpiredException.class)
    public Map<String, String> handleBidDateExpiredException(BidDateExpiredException ex)
    {
        Map<String, String> errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFound.class)
    public Map<String, String> handleProductNotFoundException(ProductNotFound ex)
    {
        Map<String, String> errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }*/
}
