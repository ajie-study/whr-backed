package com.whr.controller;

import com.whr.constant.Constant;
import com.whr.model.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description : 用户控制层
 * @Author : zhj
 * @Date: 2020-07-17 13:50
 */
@RestController
@RequestMapping("user")
public class UserController {

    public ResultModel addUser() {

        return ResultModel.builder().status(200).message(Constant.ADD_USER_SUCCESS).build();
    }
}