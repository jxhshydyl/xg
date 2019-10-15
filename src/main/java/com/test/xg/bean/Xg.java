package com.test.xg.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname Xg
 * @Description TODO
 * @Date 2019/9/22 12:31
 * @Author by liuweipeng
 */
@Data
@ToString
public class Xg {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
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
}
