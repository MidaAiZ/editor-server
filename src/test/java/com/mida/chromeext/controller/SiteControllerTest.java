package com.mida.chromeext.controller;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.ChromeExtApplication;
import com.mida.chromeext.pojo.SiteCategory;
import com.mida.chromeext.vo.SiteListQueryVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author lihaoyu
 * @date 2019/10/13 16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test-params.properties"})
public class SiteControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Environment env;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    @Test
    public void siteTest() throws Exception {
        listTest();

    }




    public SiteListQueryVo queryVo(){
        return new SiteListQueryVo();
    }


    public void listTest() throws Exception{
        SiteListQueryVo queryVo = new SiteListQueryVo();
        queryVo.setCountryCode(env.getProperty("site.countryCode"));
        queryVo.setKeyWord(env.getProperty("site.keyWord"));
        queryVo.setPageNum(Integer.parseInt(env.getProperty("site.pageNum")));
        queryVo.setPageSize(Integer.parseInt(env.getProperty("site.pageSize")));
        SiteCategory category = new SiteCategory();
        if(!StringUtils.isEmpty(env.getProperty("site.siteCategoryId"))){
        category.setCid(Integer.parseInt(env.getProperty("site.siteCategoryId")));
        }
        queryVo.setSiteCategory(category);

        MvcResult mvcResult3 = mockMvc.perform(MockMvcRequestBuilders.post("/sites/list")
                .param("queryVo", JSON.toJSONString(queryVo))).andReturn();
        Assert.assertEquals(mvcResult3.getResponse().getStatus(),200);
    }

    public void  postTest() throws Exception{


    }

}
