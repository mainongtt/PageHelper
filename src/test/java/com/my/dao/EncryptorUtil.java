package com.my.dao;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: zhangKun
 * @create: 2024-08-11 11:17
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class EncryptorUtil {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {

        // 给原账号生成加密后的用户名
        String username = stringEncryptor.encrypt("root");

        // 给原密码生成加密后的密码
        String pwd = stringEncryptor.encrypt("root");
        System.out.println(pwd);
    }
}
