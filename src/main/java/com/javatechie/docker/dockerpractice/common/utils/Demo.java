package com.javatechie.docker.dockerpractice.common.utils;

import org.springframework.stereotype.Component;

@Component
public class Demo {


    public Demo(MyDemo myDemo) {
        System.out.println("Inside Demo Constructor..!!!");
        System.out.println("Testing for git repositories..!!!");
    }
}
