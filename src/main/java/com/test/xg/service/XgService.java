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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.beans.Transient;
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

    @Transactional(rollbackFor = Exception.class)
    public void importPersonalProblem(List<PersonalProblem> personalProblemList) {
        List<List<PersonalProblem>> listArrayList=new ArrayList<List<PersonalProblem>>();
        for(int i=0;i<personalProblemList.size()/10+1;i++){
            List<PersonalProblem> personalProblems=new ArrayList<PersonalProblem>();
            for(int j=0;j<personalProblemList.size();j++){
                personalProblems.add(personalProblemList.get(i));
            }
            listArrayList.add(personalProblems);
        }
        for(List<PersonalProblem> personalProblems:listArrayList){
            xgMapper.importPersonalProblem(personalProblems);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void importNotice(List<Notice> noticeList) {
        List<List<Notice>> listArrayList=new ArrayList<List<Notice>>();
        for(int i=0;i<noticeList.size()/10+1;i++){
            List<Notice> notices=new ArrayList<Notice>();
            for(int j=0;j<noticeList.size();j++){
                notices.add(noticeList.get(i));
            }
            listArrayList.add(notices);
        }
        for(List<Notice> notices:listArrayList){
            xgMapper.importNotice(notices);
        }
    }
}
