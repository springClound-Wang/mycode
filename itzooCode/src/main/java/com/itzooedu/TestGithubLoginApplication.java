package com.itzooedu;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itzooedu.mapper")
public class TestGithubLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestGithubLoginApplication.class, args);
    }
}
