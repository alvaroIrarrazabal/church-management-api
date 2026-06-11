package com.irarrazabal.iglesiaapi.exceptions.handler;

import com.irarrazabal.iglesiaapi.exceptions.custom.AttendenceNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.custom.AuthNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.custom.MemberNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.custom.MinistryNotFoundException;
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
            MinistryNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarMinisterioNotFound(
            MinistryNotFoundException e
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
            AttendenceNotFoundExceptions.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarAttendenceNotFound(
            AttendenceNotFoundExceptions e
    ) {
        return  e.getMessage();
    }

}
