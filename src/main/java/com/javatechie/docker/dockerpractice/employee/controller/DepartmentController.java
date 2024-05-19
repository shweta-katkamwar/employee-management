package com.javatechie.docker.dockerpractice.employee.controller;

import com.javatechie.docker.dockerpractice.employee.model.Department;
import com.javatechie.docker.dockerpractice.employee.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<String>> getAllDepartment() {
        List<String> department = departmentService.getAllDepartment();
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> addNewDepartment(@RequestBody Department department) {
        //return  employeeService.addNewEmployee(emp);
        Department departmentResponse = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentResponse);
    }
}
