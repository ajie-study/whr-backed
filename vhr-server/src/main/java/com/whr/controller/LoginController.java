package com.whr.controller;

import com.google.common.base.Verify;
import com.whr.constant.Constant;
import com.whr.constant.StatusConstant;
import com.whr.dto.LoginDTO;
import com.whr.model.ResultModel;
import com.whr.service.LoginService;
import com.whr.util.RedisUtil;
import com.whr.util.RequestUtil;
import com.whr.util.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName : LoginController
 * @Description : 登录控制层
 * @Author : zhj
 * @Date: 2020-07-17 14:29
 */
@RestController
@RequestMapping
@Api(tags = "登录接口")
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private RedisUtil redisUtil;



    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultModel login(@RequestBody @Validated LoginDTO loginDTO, HttpServletRequest request){

        String ipAddress = RequestUtil.getIpAddress(request);

        String code = redisUtil.get(Constant.REDIS_VERIFY_CODE + ipAddress);

        // 1.判断验证码
        String requestCode = loginDTO.getCode();

        if(code == null || !(code.equalsIgnoreCase(requestCode))){
            return ResultModel.builder().status(StatusConstant.BAD_REQUEST).message("验证码错误").build();
        }

        return loginService.login(loginDTO);
    }


    @GetMapping("/verifyCode/*")
    @ApiOperation("获取验证码")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String ipAddress = RequestUtil.getIpAddress(request);

        VerifyCodeUtil code = new VerifyCodeUtil();
        BufferedImage image = code.getImage();
        String text = code.getText();
        // 将验证码存入redis,过期时间为60秒
        redisUtil.set(Constant.REDIS_VERIFY_CODE + ipAddress, text, 60);
        VerifyCodeUtil.output(image,resp.getOutputStream());
    }
}