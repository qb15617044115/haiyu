package com.zhixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhixin.**.mapper")
public class ZhixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhixinApplication.class,args);
    }
}
