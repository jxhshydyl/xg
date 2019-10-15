package com.test.xg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.test.xg.mapper"})
public class XgApplication {

    public static void main(String[] args) {
        SpringApplication.run(XgApplication.class, args);
    }

}
