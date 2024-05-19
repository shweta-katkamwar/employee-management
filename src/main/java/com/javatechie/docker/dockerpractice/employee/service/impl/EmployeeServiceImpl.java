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
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    //@Autowired
    //private final EmployeeStub employeeStub;

    //@Autowired
    private final EmployeeRepository employeeRepository;

    private DepartmentService departmentService;

    public List<Employee> getAllEmployee() {
        //    return employeeStub.getAllEmployeeData();
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        //return employeeStub.getEmployeeById(id);

        Optional<Employee> employeeById = employeeRepository.findById(id);

        if (employeeById.isPresent()) {
            return employeeById.get();
        }
        throw new EmployeeNotFoundException("Employee does not exist for id " + id);
    }

    public Employee addNewEmployee(Employee emp) throws DepartmentNotFoundException {
        verifyDepartment(emp.getDepartmentName());
        return employeeRepository.save(emp);
    }

    @Override
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeNotFoundException, DepartmentNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        if (!employeeOptional.isPresent()) {
            throw new EmployeeNotFoundException("Employee not present");
        }
        verifyDepartment(employee.getDepartmentName());
        employee.getAddress().setAddressId(employeeOptional.get().getAddress().getAddressId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee partialUpdateEmployeeDetails(Integer id, Map<String, String> fields) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new EmployeeNotFoundException("Employee not present");
        }
        Employee updatedEmployee = employeeOptional.get();
        for (Map.Entry<String, String> field : fields.entrySet()) {
            if (field.getKey().equals("mobileNo")) {
                updatedEmployee.setMobileNo(field.getValue());
            }
            if (field.getKey().equals("name")) {
                employeeOptional.get().setName(field.getValue());
            }

        }
        return employeeRepository.save(updatedEmployee);
//        verifyDepartment(employee.);

    }

    private void verifyDepartment(String departmentName) throws DepartmentNotFoundException {

        if (!departmentService.getAllDepartment().contains(departmentName)) {
            throw new DepartmentNotFoundException("Invalid department selected");
        }

    }

}