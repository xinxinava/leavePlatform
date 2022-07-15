package com.littlebean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(        exclude = {                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,                org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class        })
@MapperScan("com.littlebean.dao")
public class LeavePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeavePlatformApplication.class, args);
    }

}
