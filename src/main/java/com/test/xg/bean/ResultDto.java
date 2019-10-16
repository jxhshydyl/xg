package com.test.xg.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname ResultDto
 * @Description TODO
 * @Date 2019/10/16 20:05
 * @Author by liuweipeng
 */
@Data
@ToString
public class ResultDto<T> {
    private int code;
    private String message;
    private T data;
    public ResultDto(int code,String message){
        this.code=code;
        this.message=message;
    }
    public ResultDto(){
    }
    public ResultDto ok(){
        this.code=200;
        this.message="success";
        return this;
    }
    public ResultDto error(){
        this.code=-100;
        this.message="error";
        return this;
    }
}
