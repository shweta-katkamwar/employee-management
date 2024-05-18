package com.javatechie.docker.dockerpractice.employee.service;

import com.javatechie.docker.dockerpractice.employee.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

//    by default all menthods of interface are public static final

    List<String> getAllDepartment();

    Department createDepartment(Department department);
}
