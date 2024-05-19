package com.javatechie.docker.dockerpractice.employee.controller;

import com.javatechie.docker.dockerpractice.common.utils.Response;
import com.javatechie.docker.dockerpractice.employee.exceptions.DepartmentNotFoundException;
import com.javatechie.docker.dockerpractice.employee.exceptions.EmployeeNotFoundException;
import com.javatechie.docker.dockerpractice.employee.model.Employee;
import com.javatechie.docker.dockerpractice.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployee() {
        when(employeeService.getAllEmployee()).thenReturn(new ArrayList<Employee>());

        ResponseEntity<List<Employee>> allEmployee = employeeController.getAllEmployee();

        assertThat(allEmployee.getStatusCode().value()).isEqualTo(200);
        assertThat(allEmployee.getBody().size()).isEqualTo(0);
    }

    @Test
    void shouldReturnProvidedData() {
        ArrayList<Employee> employeeList = getEmployees();

        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        ResponseEntity<List<Employee>> allEmployee = employeeController.getAllEmployee();

        assertThat(allEmployee.getStatusCode().value()).isEqualTo(200);
        assertThat(allEmployee.getBody().size()).isEqualTo(2);

        List<Employee> employeeListResponse = allEmployee.getBody();

        assertTrue(ObjectUtils.isEmpty(employeeListResponse.get(0).getName()));

    }

    @Test
    void testGetEmployee() throws EmployeeNotFoundException {
        ArrayList<Employee> employeeList = getEmployees();

        when(employeeService.getEmployeeById(any(Integer.class))).thenReturn(employeeList.get(0));

        ResponseEntity<Object> employee = employeeController.getEmployee(0);

        assertThat(employee.getStatusCode().value()).isEqualTo(200);

        assertEquals(employee.getBody(), employeeList.get(0));

    }

    @Test
    void testAddNewEmployee() throws DepartmentNotFoundException {
        ArrayList<Employee> employeeList = getEmployees();
        Employee employee = employeeList.get(0);
        employee.setDob(LocalDate.parse("1993-10-20"));

        when(employeeService.addNewEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Object> objectResponseEntity = employeeController.addNewEmployee(employee);

        assertEquals(objectResponseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(((Response<Employee>) objectResponseEntity.getBody()).getPayload(), employee);
    }

    private static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employeesList = new ArrayList<>();
        employeesList.add(Employee
                .builder()
                .userName("shweta")
                .age(16)
                .mobileNo("3344556677")
                .build()
        );
        employeesList.add(Employee
                .builder()
                .userName("Govind")
                .age(15)
                .mobileNo("33445566772")
                .build()
        );
        return employeesList;
    }
}
