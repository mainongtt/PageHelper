package com.my.dao;


import com.github.pagehelper.PageInfo;
import com.my.dao.master.UserMapperMaster;
import com.my.dao.slaver.UserMapperSlaver;
import com.my.entity.User;
import com.my.util.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPageMapper1 {

    @Autowired
    private UserMapperMaster userMapperMaster;

    @Autowired
    private UserMapperSlaver userMapperSlaver;

    @Test
    public void test() throws Exception {
        ArrayList<Supplier<List<User>>> supplierList = new ArrayList<>();
        supplierList.add(() -> userMapperMaster.selectAll());
        supplierList.add(() -> userMapperSlaver.selectAll());
        PageInfo<User> pageInfo = PageUtil.multiPageInfo1(supplierList, 0, 2, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
               return o1.getUsername().compareTo(o2.getUsername());
            }
        });
        List<User> list = pageInfo.getList();
        list.stream().forEach(
                user -> System.out.printf(user.getUsername())
        );
        System.out.println("end");
    }

}
