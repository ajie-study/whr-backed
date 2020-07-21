package com.whr.exception;

import com.whr.constant.StatusConstant;
import com.whr.model.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常的处理
 * @author zhj
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {


    /**
     * @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})针对不同类型的异常
     * @ExceptionHandler//默认处理所有异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResultModel exceptionHandler(Exception e) {

        ResultModel resultModel = ResultModel.builder().status(StatusConstant.SERVER_INTERNAL_EXCEPTION).message(e.getMessage()).build();

        if( e instanceof  BadRequestException ){
            resultModel.setStatus(((BadRequestException) e).getStatus());
        }

        if( e instanceof  UnauthorizedException ){
            resultModel.setStatus(((UnauthorizedException) e).getStatus());
        }

        // 记录日志
        e.printStackTrace();
        log.error(e.getMessage(),e);
        return resultModel;

    }

}