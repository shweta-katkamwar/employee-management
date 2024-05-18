package com.javatechie.docker.dockerpractice.employee.exceptions;

public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
