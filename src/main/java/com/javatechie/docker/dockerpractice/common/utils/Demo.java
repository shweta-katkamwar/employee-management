package com.javatechie.docker.dockerpractice.common.utils;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Demo {


    public Demo(MyDemo myDemo) {
        System.out.println("Inside Demo Constructor..!!!");
    }
}
