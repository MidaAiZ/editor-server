package com.mida.chromeext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.mida.chromeext.dao", "com.mida.chromeext.mapper"})
public class ChromeExtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChromeExtApplication.class, args);
    }

}
