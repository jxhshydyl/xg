package com.test.xg.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.xg.bean.Notice;
import com.test.xg.bean.PersonalProblem;
import com.test.xg.bean.Xg;
import com.test.xg.bean.XgParam;
import com.test.xg.mapper.XgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void importPersonalProblem(List<PersonalProblem> personalProblemList) {
        List<PersonalProblem> personalProblems=new ArrayList<PersonalProblem>();
        for(int i=0;i<personalProblemList.size();i++){
            xgMapper.importPersonalProblem(personalProblemList);
        }
    }

    public void importNotice(List<Notice> noticeList) {
        xgMapper.importNotice(noticeList);
    }
}
