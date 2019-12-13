package com.se.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 2752950836525069205L;

    private boolean success;

    private String msg;

    private T data;

    @JsonIgnore
    private HttpStatus status;

    public ResponseDto() {
    }

    public ResponseDto(T data) {
        this.data = data;
        this.success = true;
        this.status = HttpStatus.OK;
    }

    public ResponseDto(boolean success, String msg, T data, HttpStatus status) {
        this.data = data;
        this.success = success;
        this.status = status;
        this.msg = msg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}