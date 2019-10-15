package com.test.xg.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname Xg
 * @Description TODO
 * @Date 2019/9/22 12:31
 * @Author by liuweipeng
 */
@Data
@ToString
public class XgParam {
    private String date;
    private String locomotive;
    private String trainNumber;
    private String driver;
    private String learningDriver;
    private String questionCategory;
    private String monitorAction;
    private String monitorProblem;
    private String brakeProblem;
    private String manipulationProblem;
    private String shuntingProblem;
    private String violationInfo;
    private String fixedResponsibility;
    private String assessmentProcessing;
    private String workshop;
    private String remarke;
    private Integer pageNum;
    private Integer pageSize;
    private String sort;
    private String sortOrder;
}
