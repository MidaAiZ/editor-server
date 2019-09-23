package com.mida.chromeext;

import com.mida.chromeext.service.UserSiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChromeExtApplicationTests {

    @Autowired
    UserSiteService userSiteService;

    @Test
    public void contextLoads() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        userSiteService.deleteSiteByUserIdAndSiteId(17, 2);
    }

}
