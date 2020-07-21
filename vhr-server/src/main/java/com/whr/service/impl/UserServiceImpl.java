package com.whr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whr.constant.StatusConstant;
import com.whr.exception.BadRequestException;
import com.whr.mapper.UserMapper;
import com.whr.model.User;
import com.whr.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName : UserServiceImpl
 * @Description : 用户实现类
 * @Author : zhj
 * @Date: 2020-07-20 15:19
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUserById(Integer uid) {

        User user = userMapper.selectById(uid);

        if (user == null) {
            throw new BadRequestException(StatusConstant.BAD_REQUEST, "查询用户不存在");
        }
        return user;
    }
}