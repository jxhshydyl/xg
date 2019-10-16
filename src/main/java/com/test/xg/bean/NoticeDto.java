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
public class NoticeDto {
    private String beginTime;
    private String endTime;
    private String notice;
}
