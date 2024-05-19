package com.javatechie.docker.dockerpractice.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class Response<T> {
    private String message;
    private T payload;

    public Response(T payload) {
        this.payload = payload;
    }
}
