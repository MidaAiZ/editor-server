package com.mida.chromeext.quartz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author lihaoyu
 * @date 2019/10/6 15:04
 */
@Service("bgPictureJobService")
public class BgPictureJobService {

    public void fun() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url =
//                "https://api.unsplash.com/photos/?client_id=13eb1a55029048fe62b7f870a010909cf4df45b971ee460df55ab50d21307671&per_page=2&query=wallpapers";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String strbody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
//        JSONArray objects = JSON.parseArray(strbody);
        System.out.println("");
    }

}
