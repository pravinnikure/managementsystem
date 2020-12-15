package com.management.managementsystem.api;

import com.management.managementsystem.Model.Employee;
import com.management.managementsystem.exception.ApplicationError;
import com.management.managementsystem.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler
{
    @Value("${api_doc_uri}")
    private String details;


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApplicationError> handleEmpNotFountException(EmployeeNotFoundException exception, WebRequest webRequest)
    {
            ApplicationError error = new ApplicationError();
            error.setCode(101);
            error.setMessage(exception.getMessage());

            error.setDetails(details);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
