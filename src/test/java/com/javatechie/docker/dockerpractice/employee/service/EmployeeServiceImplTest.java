package com.javatechie.docker.dockerpractice.employee.service;

import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import com.javatechie.docker.dockerpractice.employee.repository.EmployeeRepository;
import com.javatechie.docker.dockerpractice.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void testGetAllEmployee() {
        ArrayList<Employee> employeeList = getEmployeeList();

        when(employeeRepository.findAll()).thenReturn(getEmployeeList());

        List<Employee> allEmployee = employeeServiceImpl.getAllEmployee();

        assertEquals(allEmployee, employeeList);
    }

    @Test
    void testGetEmployeeById() throws EmployeeNotFoundException {
        ArrayList<Employee> employeeList = getEmployeeList();

        when(employeeRepository.findById(employeeList.get(0).getId()))
                .thenReturn(Optional.of(employeeList.get(0)));

        Employee employeeById = employeeServiceImpl.getEmployeeById(employeeList.get(0).getId());

        assertEquals(employeeById, employeeList.get(0));

        when(employeeRepository.findById(3)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceImpl.getEmployeeById(3);
        });
    }

    @Test
    void testAddNewEmployee() throws DepartmentNotFoundException {
        Employee employee = getEmployeeList().get(0);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee employee1 = employeeServiceImpl.addNewEmployee(employee);

        assertEquals(employee1, employee);
    }


    private static ArrayList<Employee> getEmployeeList() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee
                .builder()
                .id(1)
                .name("Govind")
                .dob(LocalDate.of(1991, 11, 8))
                .mobileNo("1234567890")
                .departmentName("Comp")
                .email("govind@gmail.com")
                .build());
        employeeList.add(Employee
                .builder()
                .id(2)
                .name("Shweta")
                .dob(LocalDate.of(1991, 11, 8))
                .mobileNo("1234567890")
                .departmentName("Comp")
                .email("govind@gmail.com")
                .build());
        return employeeList;
    }


}
