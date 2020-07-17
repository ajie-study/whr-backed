package com.whr.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName : Base64Utils
 * @Description : base64加密
 * @Author : zhj
 * @Date: 2020-07-17 16:12
 */
@Slf4j
public class Base64Util {
    /**
     * base64 编码
     *
     * @param str
     * @return
     */
    public static String encoder(String str){
        final Base64.Encoder encoder = Base64.getEncoder();
        String strEncoder = null;
        try {
            strEncoder = encoder.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("base64 编码失败");
            e.printStackTrace();
        }
        return strEncoder;
    }
    /**
     * base64 解码
     *
     * @param str
     * @return
     */
    public static String decoder(String str){
        final Base64.Decoder decoder = Base64.getDecoder();
        String strEncoder = null;
        try {
            strEncoder = new String(decoder.decode(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("base64 解码失败");
            e.printStackTrace();
        }
        return strEncoder;
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args){
        String testStr = "周洪杰";
        String encoderStr = encoder(testStr);
        System.out.println(encoderStr);
        String decoderStr = decoder(encoderStr);
        System.out.println(decoderStr);
    }
}
