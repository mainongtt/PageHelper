package com.my.service.impl;

import com.github.pagehelper.PageHelper;
import com.my.common.UserReqDto;
import com.my.dao.UserMapper;
import com.my.entity.User;
import com.my.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2022-09-28 15:40
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(UserReqDto userReqDto) {
        List<User> users = new ArrayList<>();
        try{
            users = userMapper.selectAll();
        }catch (Exception e){
            log.info("错误");
        }
        return users;
    }

}
