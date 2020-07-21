package com.whr.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @ClassName : LoginVO
 * @Description : 登录vo
 * @Author : zhj
 * @Date: 2020-07-20 14:03
 */
@Data
@Builder
public class LoginVO {
    private Integer uid;

    private String username;

    private String token;

    @Tolerate
    public LoginVO(){}
}