package com.javatechie.docker.dockerpractice.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
    //@Column
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;
    private String mobileNo;
    private String departmentName;
    private String userName;
    private String password;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


//    @JoinColumn(name = "emp_dep", referencedColumnName = "departmentId")
//    @ManyToOne(targetEntity = Department.class, cascade = CascadeType.DETACH)
////    @ManyToOne(cascade = CascadeType.ALL)
//    private Department department;


    public void calculateAge() {
        LocalDate currentDate = LocalDate.now();
        setAge(Period.between(dob, currentDate).getYears());
    }

}
