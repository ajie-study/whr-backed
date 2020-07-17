package com.whr.controller;

import com.whr.dto.LoginDTO;
import com.whr.model.ResultModel;
import com.whr.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName : LoginController
 * @Description : 登录控制层
 * @Author : zhj
 * @Date: 2020-07-17 14:29
 */
@RestController
@RequestMapping
@Api(tags = "用户登录接口")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation("登录")
    public ResultModel login(@RequestBody LoginDTO loginDTO){

        return loginService.login(loginDTO);
    }
}