package com.javatechie.docker.dockerpractice.employee.service;

import com.javatechie.docker.dockerpractice.employee.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ToDo> getToDoList() {
//        ResponseEntity<ToDo[]> responseEntity = restTemplate
//                .getForEntity("https://jsonplaceholder.typicode.com/todos", ToDo[].class);
        ResponseEntity<ToDo[]> responseEntity = restTemplate
                .exchange("https://jsonplaceholder.typicode.com/todos",
                        HttpMethod.GET, HttpEntity.EMPTY, ToDo[].class);

        ToDo[] responseEntityBody = responseEntity.getBody();
        return Arrays.stream(responseEntityBody).toList();
    }
}
