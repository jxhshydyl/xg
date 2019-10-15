package com.test.xg.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.xg.bean.Xg;
import com.test.xg.bean.XgParam;
import com.test.xg.mapper.XgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname XgService
 * @Description TODO
 * @Date 2019/9/22 12:30
 * @Author by liuweipeng
 */
@Service
public class XgService {
    @Autowired
    XgMapper xgMapper;

    public HashMap<String,Object> selectXgByCondition(XgParam xgParam){
        HashMap<String,Object> hashMap=new HashMap<>();
        PageHelper.startPage(xgParam.getPageNum(),xgParam.getPageSize(),true);
        List<Xg> xgs = xgMapper.selectXgByCondition(xgParam);

        PageInfo<Xg> pageInfo=new PageInfo<>(xgs);
        hashMap.put("total",pageInfo.getTotal());
        hashMap.put("data",pageInfo.getList());
        return hashMap;
    }
}
