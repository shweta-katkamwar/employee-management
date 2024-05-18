package com.javatechie.docker.dockerpractice.employee.repository;

import com.javatechie.docker.dockerpractice.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
