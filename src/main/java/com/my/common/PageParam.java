package com.my.common;

import com.github.pagehelper.IPage;
import lombok.Data;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2022-09-28 10:55
 */
@Data
public class PageParam<T> implements IPage {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String orderBy;
    private  T param;

    public PageParam<T> setOrderBy(String orderBy) {
        this.orderBy = orderBy; // 此处可优化 优化详情且看解析
        return this;
    }
}
