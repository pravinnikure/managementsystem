package com.management.managementsystem.exception;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmployeeNotFoundException extends RuntimeException
{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
