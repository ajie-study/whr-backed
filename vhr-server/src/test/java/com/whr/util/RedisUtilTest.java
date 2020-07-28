package com.whr.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName : RedisUtil
 * @Description : 测试redis
 * @Author : zhj
 * @Date: 2020-07-28 10:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {


    @Resource
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        boolean a = redisUtil.set("1234", "a");
    }

    @Test
    public void test2(){
        Object o = redisUtil.get("1234");
        System.out.println(o);
    }
}