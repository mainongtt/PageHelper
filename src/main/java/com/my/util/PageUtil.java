package com.my.util;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Slf4j
public class PageUtil {


    // 方法一： 全局视野法
    public static <T> PageInfo<T> multiPageInfo1(List<Supplier<List<T>>> supplierList, int pageNo, int pageSize, Comparator<T> comparator) throws Exception {
        if (CollectionUtil.isEmpty(supplierList)) {
            throw new Exception("异常");
        }
        List<T> resAllList = supplierList.stream().map(supplier -> {
            PageHelper.startPage(pageNo, pageSize, true);
            return supplier.get();
        }).flatMap(List::stream).collect(Collectors.toList());
        List<T> resList = resAllList.stream().sorted(comparator).
                limit(pageSize).collect(Collectors.toList());
        return getPageInfo(pageNo, pageSize, resAllList.size(), resList);
    }

    public static <T> PageInfo<T> getPageInfo(int pageNo, int pageSize, int total, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setList(list);
        pageInfo.setPageNum(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);
        pageInfo.setPages((total - 1) / pageSize + 1);
        return pageInfo;
    }
}
