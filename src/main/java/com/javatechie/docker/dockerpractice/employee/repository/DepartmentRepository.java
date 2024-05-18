package com.javatechie.docker.dockerpractice.employee.repository;

import com.javatechie.docker.dockerpractice.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

//    List<String> findAllDepartmentName();
}
