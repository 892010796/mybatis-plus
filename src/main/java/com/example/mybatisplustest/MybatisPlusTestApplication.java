package com.example.mybatisplustest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 注解@EnableScheduling作用：发现注解@Scheduled的任务并后台执行
 */
@SpringBootApplication
@MapperScan("com.example.mybatisplustest.mapper")
@EnableAsync
@EnableScheduling
public class MybatisPlusTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusTestApplication.class, args);
    }

}
