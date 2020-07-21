package com.whr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whr.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName : UserMapper
 * @Description : 用户数据层
 * @Author : zhj
 * @Date: 2020-07-17 15:01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);
}
