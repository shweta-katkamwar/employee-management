package com.javatechie.docker.dockerpractice.employee.service;

import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;

    Employee addNewEmployee(Employee emp) throws DepartmentNotFoundException;

    Employee updateEmployeeDetails(Employee employee) throws EmployeeNotFoundException, DepartmentNotFoundException;

    Employee partialUpdateEmployeeDetails(Integer id, Map<String, String> fields) throws EmployeeNotFoundException;
}