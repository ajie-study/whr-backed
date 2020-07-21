package com.whr.exception;

/**
 * @ClassName : UnauthorizedException
 * @Description : 未授权异常
 * @Author : zhj
 * @Date: 2020-07-20 16:04
 */
public class UnauthorizedException extends RuntimeException {

    private Integer status;

    private String message;

    public UnauthorizedException(Integer status, String message) {
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