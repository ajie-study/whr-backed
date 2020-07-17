package com.whr.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @ClassName : ResultModel
 * @Description : 统一返回的对象
 * @Author : zhj
 * @Date: 2020-07-17 13:58
 */
@Data
@Builder
public class ResultModel {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    @Tolerate
    ResultModel(){}
}