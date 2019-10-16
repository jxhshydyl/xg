package com.test.xg.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: liuweipeng
 * @Date: 16/10/2019 上午 9:45
 * @Version: 1.0
 * @Description:
 */
@Data
@ToString
public class PersonalProblem {
    private Long id;
    private String name;
    private String fixedNumber;
    private String subordinateTeam;
    private String checkingContent;
    private String date;
    private String trainNumber;
    private String locomotive;
    private String existingProblems;
    private String measures;
    private String remake;
}
