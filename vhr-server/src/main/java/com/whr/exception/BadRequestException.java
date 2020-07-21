package com.whr.exception;

/**
 * @ClassName : BadRequestException
 * @Description : 参数错误异常
 * @Author : zhj
 * @Date: 2020-07-20 15:24
 */
public class BadRequestException extends RuntimeException{

    private Integer status;

    private String message;

    public BadRequestException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}