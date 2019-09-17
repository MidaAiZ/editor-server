package com.mida.chromeext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mida.chromeext.dao.CountryDAO;
import com.mida.chromeext.pojo.Country;

@SpringBootApplication
@MapperScan({"com.mida.chromeext.dao", "com.mida.chromeext.mapper"})
public class ChromeExtApplication implements ApplicationRunner {

    private static class TempCountry{
        private String name;

        private String code;

        private String telPrefix;

        private String timeZone;

        public TempCountry(String name, String code, String telPrefix, String timeZone) {
            this.name = name;
            this.code = code;
            this.telPrefix = telPrefix;
            this.timeZone = timeZone;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", telPrefix='" + telPrefix + '\'' +
                    ", timeZone='" + timeZone + '\'' +
                    '}';
        }
    }

    @Autowired
    private CountryDAO countryDAO;

    public static void main(String[] args) {
        SpringApplication.run(ChromeExtApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<TempCountry> list = new ArrayList<>();
        while(!scanner.hasNext("gg")){
            String name = scanner.next();
            String zh_name = scanner.next();
            String code = scanner.next();
            String telPrefix = scanner.next();
            String timeZone = scanner.next();
            list.add(new TempCountry(name,code,telPrefix,timeZone));
        }
        System.out.println(list);
        for (TempCountry tempCountry : list) {
            Country country = new Country();
            Date date = new Date();
            country.setName(tempCountry.name);
            country.setCreatedAt(date);
            country.setUpdatedAt(date);
            country.setUsersCount(0);
            country.setCode(tempCountry.code);
            country.setTelPrefix(tempCountry.telPrefix);
            country.setTimeZone(tempCountry.timeZone);
            countryDAO.insert(country);
        }

    }
}
