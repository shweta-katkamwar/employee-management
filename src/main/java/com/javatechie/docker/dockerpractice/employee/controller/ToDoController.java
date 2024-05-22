package com.javatechie.docker.dockerpractice.employee.controller;

import com.javatechie.docker.dockerpractice.employee.model.ToDo;
import com.javatechie.docker.dockerpractice.employee.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @RequestMapping(value = "/api/todo-list", method = RequestMethod.GET)
    public ResponseEntity<Object> getTodoList() {
        List<ToDo> listOfToDo = toDoService.getToDoList();

        return ResponseEntity.status(HttpStatus.OK).body(listOfToDo);
    }
}
