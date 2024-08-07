package com.my.util;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.entity.MultiPageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Slf4j
public class PageUtil {


    // 方法一： 全局视野法
    public static <T> PageInfo<T> multiPageInfo1(ArrayList<Supplier<List<T>>> supplierList, int pageNo, int pageSize, Comparator<T> comparator) throws Exception {
        if (CollectionUtil.isEmpty(supplierList)) {
            throw new Exception("异常");
        }
        List<T> resAllList = supplierList.stream().map(supplier -> {
            PageHelper.startPage(1, pageNo * pageSize, true);
            return supplier.get();
        }).flatMap(List::stream).collect(Collectors.toList());
        List<T> resList = resAllList.stream().sorted(comparator).skip((pageNo - 1) * pageSize).
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

    // 二次查询法
    public static <T> MultiPageInfo multiPageInfoMap(ArrayList<Supplier<List<T>>> supplierList, int pageNo, int pageSize, Comparator<T> comparator) throws Exception {
        MultiPageInfo<T> multiPageInfo = new MultiPageInfo<T>();
        int offset = (pageNo - 1) * pageSize;
        int n = supplierList.size();
        int offsetSplit = offset / n;
        List<T> maxValue = new ArrayList<>();
        List<T> minValue = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger();
        Optional<T> totalMin = supplierList.stream().map(supplier -> {
            PageHelper.offsetPage(offsetSplit, pageSize, true);
            List<T> tempList = supplier.get();
            sum.addAndGet(tempList.size());
            Optional<T> max = tempList.stream().max(comparator);
            if (max.isPresent()) {
                maxValue.add(max.get());
            }

            return tempList;
        }).flatMap(List::stream).min(comparator);
        if (totalMin.isPresent()) {
            minValue.add(totalMin.get());
        }
        multiPageInfo.setMaxValues(maxValue);
        multiPageInfo.setMinValues(minValue);
        multiPageInfo.setTotalCount(sum.intValue());
        return multiPageInfo;
    }

    public static <T> PageInfo<T> multiPageInfo2(int total, ArrayList<Supplier<List<T>>> supplierList, int pageNo, int pageSize, Comparator<T> comparator) throws Exception {
        int n = supplierList.size();
        int totalCount = supplierList.stream()
                .mapToInt(supplier -> supplier.get().size()) // 获取每个Supplier提供的列表并转换为int
                .sum();
        int offset = ((pageNo - 1) * pageSize) / n * n - (totalCount - total);
        offset = Math.max(offset, 0);
        List<T> resAllList = supplierList.stream().map(supplier -> {
            return supplier.get();
        }).flatMap(List::stream).collect(Collectors.toList());
        List<T> resList = resAllList.stream().sorted(comparator).skip((pageNo - 1) * pageSize - offset)
                .limit(pageSize).collect(Collectors.toList());
        return getPageInfo(pageNo, pageSize, resAllList.size(), resList);
    }
}
