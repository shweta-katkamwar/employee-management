package com.javatechie.docker.dockerpractice.employee.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
