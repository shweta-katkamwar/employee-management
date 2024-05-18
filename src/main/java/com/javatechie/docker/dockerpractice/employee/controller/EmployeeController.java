package com.javatechie.docker.dockerpractice.employee.controller;

import com.javatechie.docker.dockerpractice.common.utils.Response;
import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import com.javatechie.docker.dockerpractice.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        //return employeeService.getAllEmployee();
        List<Employee> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable Integer id) {
        //return employeeService.getEmployeeById(id);
        Employee employeeById;
        try {
            employeeById = employeeService.getEmployeeById(id);
        } catch (EmployeeNotFoundException ex) {
            log.error(ex.getMessage());
            Response<String> response = new Response<>(ex.getMessage(), "this is just checking");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            Response<String> response = new Response<>(e.getMessage(), "hey something went wrong on server, Please try after sometime.");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
//         Response<String,Employee> response = new Response("Success",employeeById);
//         Response<Employee> response = new Response(employeeById);
//         return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.OK).body(employeeById);
    }

    @PostMapping("/employee")
    //public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee emp) {
    public ResponseEntity<Object> addNewEmployee(@RequestBody Employee emp) {
        //return  employeeService.addNewEmployee(emp);
        emp.calculateAge();
        Employee employee;
        try {
            employee = employeeService.addNewEmployee(emp);
        } catch (DepartmentNotFoundException ex) {
            log.error("Incorrect Department selected");
            Response<String> response = new Response<>(ex.getMessage(), "this is payload");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Response<Employee> responseOfEmploye = new Response<>("Employee Created!", employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOfEmploye);
    }

}
