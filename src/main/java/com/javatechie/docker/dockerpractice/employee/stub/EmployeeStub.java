//package com.javatechie.docker.dockerpractice.employee.stub;
//
//import com.javatechie.docker.dockerpractice.employee.model.Employee;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//public class EmployeeStub {
//    private static List<Employee> employee;
//    static {
//        employee = new ArrayList<>();
//        employee.add(new Employee(1001,"Govind","govind@gmail.com"));
//        employee.add(new Employee(1002,"Shweta","shweta@gmail.com"));
//        employee.add(new Employee(1003,"John","john@gmail.com"));
//    }
//    public List<Employee> getAllEmployeeData() {
//        return employee;
//    }
//
//    public Employee getEmployeeById(Integer id) {
//        Optional<Employee> employeeOptional = employee.stream().filter(employee1 -> Objects.equals(employee1.getId(), id)).findFirst();
//        if(employeeOptional.isPresent()) {
//            return employeeOptional.get();
//        }
////        for(Employee emp:employee) {
////            if(emp.getId()==id) {
////                return emp;
////            }
////        }
////        for(int i=0; i<=employee.size(); i++) {
////            if(id == employee.get(i).getId()) {
////                return employee.get(i);
////            } }
//        return null;
//    }
//
//}
