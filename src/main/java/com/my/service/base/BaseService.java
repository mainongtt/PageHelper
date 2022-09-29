package com.my.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.common.PageParam;

import java.util.List;

public interface BaseService<Param, Result> {

     default PageInfo<Result> page(PageParam<Param> param){
         return PageHelper.startPage(param).doSelectPageInfo(
                 ()->list(param.getParam())
         );
     }

    List<Result> list(Param param);
}
