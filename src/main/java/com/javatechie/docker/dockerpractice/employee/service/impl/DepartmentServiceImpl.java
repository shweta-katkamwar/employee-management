package com.javatechie.docker.dockerpractice.employee.service.impl;

import com.javatechie.docker.dockerpractice.employee.model.Department;
import com.javatechie.docker.dockerpractice.employee.repository.DepartmentRepository;
import com.javatechie.docker.dockerpractice.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<String> getAllDepartment() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();

        for (Department dept : allDepartments) {
            departmentNames.add(dept.getDepartmentName());
        }
        return departmentNames;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
