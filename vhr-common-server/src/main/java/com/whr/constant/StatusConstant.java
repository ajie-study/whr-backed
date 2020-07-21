package com.whr.constant;

/**
 * @ClassName : StatusConstant
 * @Description : 返回状态码
 * @Author : zhj
 * @Date: 2020-07-17 17:08
 */
public class StatusConstant {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * 参数错误
     */
    public static final Integer BAD_REQUEST = 400;

    /**
     * 系统内部错误
     */
    public static final Integer SERVER_INTERNAL_EXCEPTION = 500;

    /**
     * 未认证错误
     */
    public static final Integer UNAUTHORIZED = 401;
}