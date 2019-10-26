package com.test.xg.controller;

import com.test.xg.bean.*;
import com.test.xg.service.XgService;
import com.test.xg.util.CheckObjectIsNullUtils;
import com.test.xg.util.ImportExcelUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @PostMapping("/importPersonalProblem")
    @ResponseBody
    public ResultDto importPersonalProblem(@RequestParam(value="excelFile",required=false) MultipartFile file){
        try {
            if(file==null||file.isEmpty()){
                throw new RuntimeException("文件不能为空！");
            }
            InputStream inputStream = file.getInputStream();
            ImportExcelUtils importExcelUtils=new ImportExcelUtils();
            List<List<List<Object>>> bankListByExcel = importExcelUtils.getBankListByExcel(inputStream, "test.xls");
            String name=null;
            String fixedNumber=null;
            String subordinateTeam=null;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            List<PersonalProblem> personalProblemList=new ArrayList<PersonalProblem>();
            for(List<List<Object>> listList:bankListByExcel){
                for(int i=0;i<listList.size();i++){
                    List<Object> list=listList.get(i);
                    if(list.size() <7){
                        continue;
                    }
                    if(i == 0){
                        name = (String)list.get(1);
                        fixedNumber = (String)list.get(4);
                        subordinateTeam = (String)list.get(7);
                    }else if(i == 1){
                        i1 = list.indexOf("检查内容");
                        i2 = list.indexOf("日期");
                        i3 = list.indexOf("车次");
                        i4 = list.indexOf("机车");
                        i5 = list.indexOf("存在问题");
                        i6 = list.indexOf("帮教措施");
                    }else{
                        PersonalProblem personalProblem=new PersonalProblem();
                        personalProblem.setName(StringUtils.isEmpty(name)?"":name);
                        personalProblem.setFixedNumber(StringUtils.isEmpty(fixedNumber)?"":fixedNumber);
                        personalProblem.setSubordinateTeam(StringUtils.isEmpty(subordinateTeam)?"":subordinateTeam);
                        personalProblem.setCheckingContent(StringUtils.isEmpty(list.get(i1))?"":(String)list.get(i1));
                        personalProblem.setDate(StringUtils.isEmpty(list.get(i2))?"":(String)list.get(i2));
                        personalProblem.setTrainNumber(StringUtils.isEmpty(list.get(i3))?"":(String)list.get(i3));
                        personalProblem.setLocomotive(StringUtils.isEmpty((String)list.get(i4))?"":(String)list.get(i4));
                        personalProblem.setExistingProblems(StringUtils.isEmpty((String)list.get(i5))?"":(String)list.get(i5));
                        personalProblem.setMeasures(StringUtils.isEmpty((String)list.get(i6))?"":(String)list.get(i6));
                        boolean b = CheckObjectIsNullUtils.objCheckFieldIsNotNull(personalProblem);
                        if(b){
                            personalProblemList.add(personalProblem);
                        }
                    }
                }
            }
            xgService.importPersonalProblem(personalProblemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDto().error();
        }
        return new ResultDto().ok();
    }

    @PostMapping("/selectPersonalProblemByCondition")
    @ResponseBody
    public HashMap<String,Object> selectPersonalProblemByCondition(PersonalProblemDto personalProblemDto){
        try {
            if(personalProblemDto.getName() == null || "".equals(personalProblemDto.getName())){
                throw new RuntimeException("参数不能为空");
            }
            HashMap<String, Object> hashMap = xgService.selectPersonalProblemByCondition(personalProblemDto);
            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/importNotice")
    @ResponseBody
    public ResultDto importNotice(@RequestParam(value="excelFile1",required=false)MultipartFile file){
        try {
            if(file.isEmpty()){
                throw new RuntimeException("文件不能为空！");
            }
            InputStream inputStream = file.getInputStream();
            ImportExcelUtils importExcelUtils=new ImportExcelUtils();
            List<List<List<Object>>> bankListByExcel = importExcelUtils.getBankListByExcel(inputStream, "test.xls");
            List<Notice> noticeList=new ArrayList<Notice>();
            for(List<List<Object>> listList:bankListByExcel){
                for(int i=0;i<listList.size();i++){
                    List<Object> list=listList.get(i);
                    Notice notice=new Notice();
                    notice.setDate((String)list.get(0));
                    notice.setNotice((String)list.get(1));
                    noticeList.add(notice);
                }
            }
            xgService.importNotice(noticeList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDto().error();
        }
        return new ResultDto().ok();
    }

    @PostMapping("/selectNotice")
    @ResponseBody
    public ResultDto selectNotice(NoticeDto noticeDto){
        ResultDto resultDto = new ResultDto();
        try {
            if(noticeDto.getNotice() == null || "".equals(noticeDto.getNotice())){
                return resultDto.error();
            }
            List<Notice> notices = xgService.selectNotice(noticeDto);
            resultDto.setData(notices);
            return resultDto.ok();
        }catch (Exception e){
            e.printStackTrace();
            return resultDto.error();
        }
    }

    @PostMapping("/importRecentConvey")
    @ResponseBody
    public ResultDto importRecentConvey(@RequestParam(value="excelFile2",required=false)MultipartFile file){
        try {
            if(file.isEmpty()){
                throw new RuntimeException("文件不能为空！");
            }
            InputStream inputStream = file.getInputStream();
            ImportExcelUtils importExcelUtils=new ImportExcelUtils();
            List<List<List<Object>>> bankListByExcel = importExcelUtils.getBankListByExcel(inputStream, "test.xls");
            List<RecentConvey> recentConveyList=new ArrayList<RecentConvey>();
            for(List<List<Object>> listList:bankListByExcel){
                for(int i=0;i<listList.size();i++){
                    List<Object> list=listList.get(i);
                    RecentConvey recentConvey=new RecentConvey();
                    recentConvey.setDate((String)list.get(0));
                    recentConvey.setNotice((String)list.get(1));
                    recentConveyList.add(recentConvey);
                }
            }
            xgService.importRecentConvey(recentConveyList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDto().error();
        }
        return new ResultDto().ok();
    }
}
