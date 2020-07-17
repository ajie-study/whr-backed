package com.whr.service;

import com.whr.dto.LoginDTO;
import com.whr.model.ResultModel;

/**
 * @ClassName : LoginService
 * @Description : 登录业务层
 * @Author : zhj
 * @Date: 2020-07-17 14:45
 */
public interface LoginService {


    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    ResultModel login(LoginDTO loginDTO);
}