package com.javatechie.docker.dockerpractice.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;

    //    @NotBlank
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @NotBlank(message = "name should not be empty")
    @Size(min = 2, message = "name should be atleast 2 characters long")
    private String name;
    @Email(message = "Please provide valid email")
    @NotBlank(message = "email should not be blank")
    private String email;

    @NotNull(message = "DOB should not be blank")
    @Past
    private LocalDate dob;

    private Integer age;

    @Size(min = 10, max = 10, message = "Mobile number should be of length 10")
    private String mobileNo;

    @NotBlank
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
