package com.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 刘佳俊
 */
@SpringBootApplication
@MapperScan(value = "com.mybatisplus.dao.mapper")
public class MybatisplueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplueApplication.class, args);
    }

}
