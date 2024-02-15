package com.buyanne;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(BigEventApplication.class ,args);
    }
}
