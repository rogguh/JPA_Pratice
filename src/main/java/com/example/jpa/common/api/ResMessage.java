package com.example.jpa.common.api;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResMessage {

    private HttpStatusEnum httpStatusEnum;
    private String message;
    private Object data;

    @Builder
    public ResMessage(HttpStatusEnum httpStatusEnum, String message, Object data){
        this.httpStatusEnum = httpStatusEnum;
        this.message = message;
        this.data = data;
    }
}