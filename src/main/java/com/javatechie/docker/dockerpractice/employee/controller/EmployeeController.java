package com.javatechie.docker.dockerpractice.employee.controller;

import com.javatechie.docker.dockerpractice.common.utils.Response;
import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import com.javatechie.docker.dockerpractice.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        //return employeeService.getAllEmployee();
        List<Employee> allEmployee = employeeService.getAllEmployee();
        //allEmployee.get(0).setName("Hacked");
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
    public ResponseEntity<Object> addNewEmployee(@RequestBody @Valid Employee emp) {
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

    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployeeDetails(@RequestBody Employee employee, @PathVariable Integer id) {
        employee.setId(id);
        employee.calculateAge();
        Employee updatedEmployee;
        try {
            updatedEmployee = employeeService.updateEmployeeDetails(employee);
        } catch (EmployeeNotFoundException ex) {
//            throw new RuntimeException(ex);
            log.error("employee doesn't exist");
            Response response = new Response("Employee doesn't exist",
                    "Existing employees only can be updated");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (DepartmentNotFoundException ex) {
            log.error("Incorrect department added");
            Response response = new Response(employee.getDepartmentName(),
                    "is not valid department");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Response<Employee> response = new Response<>("Details Updated", updatedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Object> partialUpdateEmployeeDetails(@PathVariable Integer id,
                                                               @RequestBody Map<String, String> fields) {
        Employee updatedEmployee;
        try {
            updatedEmployee = employeeService.partialUpdateEmployeeDetails(id, fields);
        } catch (EmployeeNotFoundException ex) {
            log.error("Not an existing employee");
            Response response = new Response("id : " + id, "Not exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Response<Employee> response = new Response<>("Details Updated", updatedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
