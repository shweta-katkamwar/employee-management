package com.javatechie.docker.dockerpractice.controller;

import com.javatechie.docker.dockerpractice.employee.controller.DepartmentController;
import com.javatechie.docker.dockerpractice.employee.model.Department;
import com.javatechie.docker.dockerpractice.employee.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void testGetAllDepartment() {
        List<String> departmentList = new ArrayList<>();
        departmentList.add("Comp");
        departmentList.add("EC");

        when(departmentService.getAllDepartment()).thenReturn(departmentList);

        //ResponseEntity.status(HttpStatus.OK).body(department);
        ResponseEntity<List<String>> allDepartment = departmentController.getAllDepartment();

        assertEquals(allDepartment.getStatusCode(), HttpStatus.OK);
        assertEquals(allDepartment.getBody(), departmentList);
    }

    @Test
    public void testAddNewDepartment() {
        Department department = new Department(1, "IT", "ITHead");

        when(departmentService.createDepartment(department)).thenReturn(department);

        ResponseEntity<Department> departmentResponse = departmentController.addNewDepartment(department);

        assertEquals(departmentResponse.getStatusCode(), HttpStatus.CREATED);
        assertEquals(departmentResponse.getBody(), department);

    }

}
