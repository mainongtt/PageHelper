package com.my.dao;

import com.github.pagehelper.PageInfo;
import com.my.dao.master.UserMapperMaster;
import com.my.dao.slaver.UserMapperSlaver;
import com.my.entity.MultiPageInfo;
import com.my.entity.User;
import com.my.util.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Supplier;

/**
 * @description: 测试方法二
 * @author: zhangKun
 * @create: 2024-07-23 20:39
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPageMapper2 {
    @Autowired
    private UserMapperMaster userMapperMaster;

    @Autowired
    private UserMapperSlaver userMapperSlaver;

    @Test
    public void test() throws Exception {
        int pageNo = 2;
        int pageSize = 5;
        ArrayList<Supplier<List<User>>> supplierList1 = new ArrayList<>();
        supplierList1.add(() -> userMapperMaster.selectAll());
        supplierList1.add(() -> userMapperSlaver.selectAll());

        MultiPageInfo multiPageInfo = PageUtil.multiPageInfoMap(supplierList1, pageNo, pageSize, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int username1 = Integer.parseInt(o1.getUsername());
                int username2 = Integer.parseInt(o2.getUsername());
                return username1 - username2;
            }
        });
        List<User> maxValue = multiPageInfo.getMaxValues();
        List<User> minValue = multiPageInfo.getMinValues();
        int totalCount = multiPageInfo.getTotalCount();
        if(maxValue.size() == 0 || minValue.size() == 0){
            return;
        }
        ArrayList<Supplier<List<User>>> supplierList2 = new ArrayList<>();
        supplierList2.add(() -> userMapperMaster.selectBetween(minValue.get(0).getUsername(), maxValue.get(0).getUsername()));
        supplierList2.add(() -> userMapperSlaver.selectBetween(minValue.get(0).getUsername(), maxValue.get(1).getUsername()));
        PageInfo<User> pageInfo = PageUtil.multiPageInfo2(totalCount, supplierList2, pageNo, pageSize, new Comparator<User>() {
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
