package com.example.springbootlog.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class ConfigEncryptUtils {

    public static void main(String[] args) {

        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //生成秘钥的公钥
        config.setPassword("secret");
        //应用配置
        encryptor.setConfig(config);
        //明文密码

        String plaintext = "BLUEMIX1";

        //加密
        String ciphertext = encryptor.encrypt(plaintext);
        System.out.println(plaintext + "加密后: " + ciphertext);

        //解密 过程
        String pText = encryptor.decrypt(ciphertext);
        System.out.println(ciphertext + "  解密后:  " + pText);
    }
}
