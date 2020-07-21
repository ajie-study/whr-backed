package com.whr.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName : UserDTO
 * @Description : 用户用于接受的对象
 * @Author : zhj
 * @Date: 2020-07-17 14:37
 */
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, message = "用户名长度不能低于4")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, message = "密码长度不能低于4")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Size(max = 4)
    private String code;

}