package com.whr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whr.constant.StatusConstant;
import com.whr.mapper.UserMapper;
import com.whr.dto.LoginDTO;
import com.whr.model.ResultModel;
import com.whr.model.User;
import com.whr.service.LoginService;
import com.whr.util.AESUtil;
import com.whr.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName : LoginServiceImpl
 * @Description : 登录实现类
 * @Author : zhj
 * @Date: 2020-07-17 14:46
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultModel login(LoginDTO loginDTO) {

        // 1.判断验证码

        String username = loginDTO.getUsername();

        log.info("用户开始{}登录", username);

        // 2.查询数据库
        User userDB = userMapper.username(username);

        // 3.对比密码
        // base64解密
        String pwd = Base64Util.decoder(loginDTO.getPassword());

        // AES加密
        String encryptPWD = AESUtil.encrypt(pwd);

        if (!encryptPWD.equals(userDB.getPassword())) {
            // 密码错误
            return ResultModel.builder().status(StatusConstant.BAD_REQUEST).message("账号或密码错误").build();
        }

        // 4.生成token

        ResultModel resultModel = ResultModel.builder().status(StatusConstant.SUCCESS).build();

        return resultModel;
    }
}