package com.javatechie.docker.dockerpractice.common.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Demo {

    public Demo(MyDemo myDemo) {

        System.out.println("Inside Demo Constructor..!!!");
        System.out.println("Testing for git repositories..!!!");
        System.out.println("Testing for git pull..!!!");
    }

    public static void main(String[] args) {
        new Demo().printR(5);
        Demo d = new Demo(null);
    }

    private void printR(int n) {
        if (n == 0)
            return;
        System.out.println(n + " ");
        printR(n - 1);
    }
}
