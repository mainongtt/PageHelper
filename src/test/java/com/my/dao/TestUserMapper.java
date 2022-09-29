package com.my.dao;

import com.my.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2022-09-28 21:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<User> users = userMapper.selectAll();
        System.out.println(users.size());
    }
}
