package com.test.xg.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.xg.bean.*;
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
        int count=10;
        int init=0;
        for(int i=0;i<(personalProblemList.size()/10+1);i++){
            List<PersonalProblem> personalProblems=new ArrayList<PersonalProblem>();
            for(int j=init;j<count;j++){
                personalProblems.add(personalProblemList.get(j));
            }
            listArrayList.add(personalProblems);
            init=count;
            count=count+10;
            if(init>=personalProblemList.size()){
                break;
            }
            if(count>personalProblemList.size()){
                count=personalProblemList.size();
            }
        }
        for(List<PersonalProblem> personalProblems:listArrayList){
            xgMapper.importPersonalProblem(personalProblems);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void importNotice(List<Notice> noticeList) {
        List<List<Notice>> listArrayList=new ArrayList<List<Notice>>();
        int count=10;
        int init=0;
        for(int i=0;i<(noticeList.size()/10+1);i++){
            List<Notice> noticeArrayList=new ArrayList<Notice>();
            for(int j=init;j<count;j++){
                noticeArrayList.add(noticeList.get(j));
            }
            listArrayList.add(noticeArrayList);
            init=count;
            count=count+10;
            if(init>=noticeList.size()){
                break;
            }
            if(count>noticeList.size()){
                count=noticeList.size();
            }
        }
        for(List<Notice> notices:listArrayList){
            xgMapper.importNotice(notices);
        }
    }

    public HashMap<String, Object> selectPersonalProblemByCondition(PersonalProblemDto personalProblemDto) {
        HashMap<String,Object> hashMap=new HashMap<>();
        PageHelper.startPage(personalProblemDto.getPageNum(),personalProblemDto.getPageSize(),true);
        List<PersonalProblem> personalProblems = xgMapper.selectPersonalProblemByCondition(personalProblemDto);
        PageInfo<PersonalProblem> pageInfo=new PageInfo<>(personalProblems);
        hashMap.put("total",pageInfo.getTotal());
        hashMap.put("data",pageInfo.getList());
        return hashMap;
    }
}
