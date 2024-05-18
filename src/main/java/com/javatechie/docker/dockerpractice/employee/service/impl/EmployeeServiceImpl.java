package com.javatechie.docker.dockerpractice.employee.service.impl;

import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import com.javatechie.docker.dockerpractice.employee.repository.EmployeeRepository;
import com.javatechie.docker.dockerpractice.employee.service.DepartmentService;
import com.javatechie.docker.dockerpractice.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    //@Autowired
    //private final EmployeeStub employeeStub;

    //@Autowired
    private final EmployeeRepository repository;

    private DepartmentService departmentService;

    public List<Employee> getAllEmployee() {
        //    return employeeStub.getAllEmployeeData();
        return repository.findAll();
    }

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        //return employeeStub.getEmployeeById(id);

        Optional<Employee> employeeById = repository.findById(id);

        if (employeeById.isPresent()) {
            return employeeById.get();
        }
        throw new EmployeeNotFoundException("Employee does not exist for id " + id);
    }

    public Employee addNewEmployee(Employee emp) throws DepartmentNotFoundException {
        verifyDepartment(emp);
        return repository.save(emp);
    }

    public void verifyDepartment(Employee emp) throws DepartmentNotFoundException {
        String departmentName = emp.getDepartmentName();

        if (!departmentService.getAllDepartment().contains(departmentName)) {
            throw new DepartmentNotFoundException("Invalid department selected");
        }

    }
}