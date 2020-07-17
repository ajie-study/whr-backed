package com.whr.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.logging.Level;

/**
 * AES工具类，密钥必须是16位字符串
 */
@Slf4j
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 自定义密码
     */
    private static final String ASSETS_DEV_PWD_FIELD = "whr";

    public static String getAssetsDevPwdField() {
        return ASSETS_DEV_PWD_FIELD;
    }

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(AESUtil.ASSETS_DEV_PWD_FIELD));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64Utils.encodeToString(result);
        } catch (Exception e) {
            log.error("AES 加密失败");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(AESUtil.ASSETS_DEV_PWD_FIELD));

            //执行操作
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
            String s = new String(result, "utf-8");
            return s;
        } catch (Exception e) {
            log.error("AES 解密失败");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            //AES 要求密钥长度为 128
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.error("生成加密秘钥失败");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String origin = "周洪杰";
//        String encrypt = AESUtil.encrypt(origin, AESUtil.ASSETS_DEV_PWD_FIELD);
//        String decrypt = AESUtil.decrypt(encrypt, AESUtil.ASSETS_DEV_PWD_FIELD);
//        System.out.println(origin);
//        System.out.println(encrypt);
//        System.out.println(decrypt);

        System.out.println(AESUtil.decrypt("LCBCittkiUPrBJ1w8jn21w=="));
    }
    
}