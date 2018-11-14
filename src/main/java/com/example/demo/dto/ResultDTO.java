package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;


/**
 * Created by cai on 24/05/2017.
 */
@Data
@Builder
public class ResultDTO<T> {

    @Builder.Default
    private Boolean isSuccess = Boolean.TRUE;
    private String message;
    @Builder.Default
    private String code = "200";
    private T data;

    public ResultDTO() {
    }

    public ResultDTO(Boolean isSuccess, String message, String code, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
        this.data = data;
    }

}
