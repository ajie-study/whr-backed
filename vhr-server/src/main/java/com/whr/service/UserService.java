package com.whr.service;

import com.whr.model.User;

/**
 * @ClassName : UserService
 * @Description :
 * @Author : zhj
 * @Date: 2020-07-20 15:14
 */
public interface UserService {
    /**
     * 根据用户id查询用户信息
     * @param uid
     * @return
     */
    User selectUserById(Integer uid);
}