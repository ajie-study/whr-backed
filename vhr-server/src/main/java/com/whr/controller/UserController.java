package com.whr.controller;

import com.alibaba.fastjson.JSONObject;
import com.whr.constant.StatusConstant;
import com.whr.model.ResultModel;
import com.whr.model.User;
import com.whr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName : UserController
 * @Description : 用户控制层
 * @Author : zhj
 * @Date: 2020-07-17 13:50
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户信息接口")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("{uid}")
    @ApiOperation("根据id获取用户信息")
    public ResultModel addUser(@PathVariable("uid") Integer uid) {

        User user = userService.selectUserById(uid);

        return ResultModel.builder().status(StatusConstant.SUCCESS).data(user).build();
    }
}