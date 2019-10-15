package com.test.xg.controller;

import com.test.xg.bean.XgParam;
import com.test.xg.service.XgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2019/9/22 12:29
 * @Author by liuweipeng
 */
@RequestMapping("/index")
@Controller
public class IndexController {
    @Autowired
    XgService xgService;

    @GetMapping()
    public String index(){
        return "index";
    }

    @PostMapping()
    @ResponseBody
    public HashMap<String,Object> selectXgByCondition(XgParam xgParam){
        try {
            HashMap<String, Object> hashMap = xgService.selectXgByCondition(xgParam);
            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
