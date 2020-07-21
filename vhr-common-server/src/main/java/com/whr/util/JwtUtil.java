package com.whr.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName : com.demo.util.JwtUtil
 * @Description : jwt工具类
 * @Author : zhj
 * @Date: 2020-07-20 11:50
 */
@Data
public class JwtUtil {

    /**
     * 自定义秘钥
     */
    public static final  String secret = "zhj123456";
    /**
     * 过期时间为一天
     */
    public static final long expire = 24 * 60 * 60 * 1000;

    /**
     * 请求头
     */
    public static final String header  = "Authorization";


    /**
     * 生成token
     * @param subject  json字符串
     * @return
     */
    public static String createToken (String subject){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public static Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
           throw new RuntimeException("解析token出错,请重新登录");
        }
    }
    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public static boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public static Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户从token中
     */
    public static String getUserFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    public static void main(String[] args) {
        String userFromToken = JwtUtil.getUserFromToken("131231");
        System.out.println(userFromToken);
    }

}