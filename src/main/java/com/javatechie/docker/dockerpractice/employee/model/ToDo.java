package com.javatechie.docker.dockerpractice.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    public int userId;
    public int id;
    public String title;
    public boolean completed;
}