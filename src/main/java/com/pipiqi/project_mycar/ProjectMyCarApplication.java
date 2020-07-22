package com.pipiqi.project_mycar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.pipiqi.project_mycar.dao")
@ServletComponentScan(basePackages = "com.pipiqi.project_mycar.config")
public class ProjectMyCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMyCarApplication.class, args);
    }

}
