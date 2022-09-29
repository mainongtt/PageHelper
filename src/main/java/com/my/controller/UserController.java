package com.my.controller;

import com.github.pagehelper.PageInfo;
import com.my.common.PageParam;
import com.my.common.UserReqDto;
import com.my.entity.User;
import com.my.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2022-09-28 15:58
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/page")
    public PageInfo<User> queryList(@RequestBody UserReqDto userReqDto){
        //设置参数
        PageParam pageParam = new PageParam();
        pageParam.setPageNum(userReqDto.getPageNum());
        pageParam.setPageSize(userReqDto.getPageSize());
        //调用分页
        PageInfo<User> page = userService.page(pageParam);
        return page;
    }

    @PostMapping(path = "/api/list")
    public List<User> list(@RequestBody UserReqDto userReqDto) {
        return userService.list(userReqDto);
    }
}
