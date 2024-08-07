package com.my.entity;

import java.util.List;
public class MultiPageInfo<T> {
    private List<T> maxValues; // 存储最大值的列表
    private List<T> minValues; // 存储最小值的列表
    private int totalCount;    // 总数量

    public MultiPageInfo() {
    }

    // 构造函数
    public MultiPageInfo(List<T> maxValues, List<T> minValues, int totalCount) {
        this.maxValues = maxValues;
        this.minValues = minValues;
        this.totalCount = totalCount;
    }

    // getter 和 setter 方法
    public List<T> getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(List<T> maxValues) {
        this.maxValues = maxValues;
    }

    public List<T> getMinValues() {
        return minValues;
    }

    public void setMinValues(List<T> minValues) {
        this.minValues = minValues;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
