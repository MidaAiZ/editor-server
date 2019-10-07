package com.mida.chromeext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan({"com.mida.chromeext.mapper", "com.mida.chromeext.dao"})
@Configuration
public class ChromeExtApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChromeExtApplication.class, args);
    }
}
