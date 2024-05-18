package com.javatechie.docker.dockerpractice.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private String message;
    private T payload;

    public Response(T payload) {
        this.payload = payload;
    }
}
