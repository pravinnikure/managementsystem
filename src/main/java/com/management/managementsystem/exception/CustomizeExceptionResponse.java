package com.management.managementsystem.exception;


import com.management.managementsystem.Model.ErrorMesseges;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class CustomizeExceptionResponse extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage()
                ,request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleEmpNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
                , request.getDescription(false));

        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMesseges.NO_EMPLOYEE_FOUND
                .getHttpStatus().name(), exceptionResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.set("header name", "header value");
        return handleExceptionInternal(ex, genericResponse,headers,ErrorMesseges.NO_EMPLOYEE_FOUND.getHttpStatus(),request);
    }

    @ExceptionHandler(DeletionException.class)
    public final ResponseEntity<Object> handleDeletionException(DeletionException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
                , request.getDescription(false));

        GenericResponse<ExceptionResponse> genericResponse = new GenericResponse<>(false, ErrorMesseges.COULD_NOT_DELETE_RECORD
                .getHttpStatus().name(), exceptionResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.set("header name", "header value");
        return handleExceptionInternal(ex, genericResponse,headers,ErrorMesseges.COULD_NOT_DELETE_RECORD.getHttpStatus(),request);
    }
}
