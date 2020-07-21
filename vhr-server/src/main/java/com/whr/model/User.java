package com.whr.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName : User
 * @Description : 用户
 * @Author : zhj
 * @Date: 2020-07-17 14:53
 */
@Data
@TableName("user")
public class User {

    @TableId
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private String phone;

    private String email;

    private Date createTime;

    private Date updateTime;
}