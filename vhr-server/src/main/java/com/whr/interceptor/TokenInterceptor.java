package com.whr.interceptor;

import com.whr.constant.Constant;
import com.whr.constant.StatusConstant;
import com.whr.exception.UnauthorizedException;
import com.whr.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName : TokenInterceptor
 * @Description : token拦截器
 * @Author : zhj
 * @Date: 2020-07-20 15:54
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${exclude-path}")
    private String excludePath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求方法
        String requestMethod = request.getMethod();
        if (requestMethod.contains("OPTIONS") || requestMethod.contains("options")) {
            return true;
        }

        // 获取token
        String token = request.getHeader(JwtUtil.header);

        if (StringUtils.isBlank(token)) {
            throw new UnauthorizedException(StatusConstant.UNAUTHORIZED, Constant.UNAUTHORIZED_AGAIN_LOGIN);
        }
        // 判断token是否有效
        try {
            JwtUtil.getUserFromToken(token);
        } catch (Exception exception) {
            throw new UnauthorizedException(StatusConstant.UNAUTHORIZED, Constant.UNAUTHORIZED_AGAIN_LOGIN);
        }

        // 判断是否过期
        Date expirationDateFromToken = JwtUtil.getExpirationDateFromToken(token);

        if (JwtUtil.isTokenExpired(expirationDateFromToken)) {
            throw new UnauthorizedException(StatusConstant.UNAUTHORIZED, Constant.TOKEN_EXPIRED);
        }

        return true;
    }

}