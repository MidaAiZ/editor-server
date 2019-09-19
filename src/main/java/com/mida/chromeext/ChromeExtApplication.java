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
            String temp = scanner.nextLine();
            String[] strings = temp.split(" ");
            int len = strings.length;
            String name ="";
            for(int i = 0; i < len - 4; i++){
                if(i == 0)
                name += strings[i];
                else{
                    name += " "+strings[i];
                }
            }
            String zh_name = strings[len-4];
            String code = strings[len-3];
            String telPrefix = strings[len-2];
            String timeZone = strings[len-1];
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
