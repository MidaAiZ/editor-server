package net.tabplus.api.components.quartz.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.tabplus.api.modules.dao.BgPictureDAO;
import net.tabplus.api.modules.pojo.BgPicture;
import net.tabplus.api.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/6 15:04
 */
@Service("bgPictureJobService")
public class BgPictureJobService {

    @Autowired
    BgPictureDAO bgPictureDAO;

    public void addBgPictures() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        for (int i = 30; i <= 60; i++) {
            String url =
                    "https://api.unsplash.com/photos?client_id=13eb1a55029048fe62b7f870a010909cf4df45b971ee460df55ab50d21307671&per_page=30&query=wallpapers&page=" + i;

            String strBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            JSONArray jsonArray = JSONObject.parseArray(strBody);
            Date date = new Date();
            List<BgPicture> list = new ArrayList<>();
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                JSONObject urls = (JSONObject) (jsonObject.get("urls"));
                String wallPaperUrl = urls.get("regular").toString();
                String title = jsonObject.get("id").toString();
                BgPicture bgPicture = BgPicture.builder().src(wallPaperUrl).createdAt(date).createdBy(NumConst.NUM0).title(title).build();
                list.add(bgPicture);
            }
            System.out.println(list.size());
            bgPictureDAO.batchInsertBgPicture(list);
        }
        System.out.println("结束");

    }


}
