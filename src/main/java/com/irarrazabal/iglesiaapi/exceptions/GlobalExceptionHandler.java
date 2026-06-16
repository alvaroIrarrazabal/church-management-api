package com.irarrazabal.iglesiaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> manejarValidaciones(

            MethodArgumentNotValidException ex

    ) {

        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        error -> error.getDefaultMessage()
                )
                .toList();

    }


    @ExceptionHandler(
            MemberNotFoundExceptions.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarEmpleadosNotFound(
            MemberNotFoundExceptions e
    ) {
        return  e.getMessage();
    }


    @ExceptionHandler(
            MinisterioNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarMinisterioNotFound(
            MinisterioNotFoundException e
    ) {
        return  e.getMessage();
    }

    @ExceptionHandler(
            AuthNotFoundExceptions.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarAuthNotFound(
            AuthNotFoundExceptions e
    ) {
        return  e.getMessage();
    }



    @ExceptionHandler(
            TitheNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarAuthNotFound(
            TitheNotFoundException e
    ) {
        return  e.getMessage();
    }

    @ExceptionHandler(
            OfferingNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarAuthNotFound(
            OfferingNotFoundException e
    ) {
        return  e.getMessage();
    }





}
