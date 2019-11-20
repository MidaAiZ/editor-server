package net.tabplus.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan({"net.tabplus.api.modules.*"})
@Configuration
public class TabplusApplication {
    public static void main(String[] args) {
        SpringApplication.run(TabplusApplication.class, args);
    }
}
