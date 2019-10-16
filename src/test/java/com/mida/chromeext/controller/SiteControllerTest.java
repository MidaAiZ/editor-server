package com.mida.chromeext.controller;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.ChromeExtApplication;


/**
 * @author lihaoyu
 * @date 2019/10/13 16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test/test-params-site.properties"})
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
        queryVo.setCountryCode(env.getProperty("countryCode"));
        queryVo.setKeyWord(env.getProperty("keyWord"));
        queryVo.setPageNum(Integer.parseInt(env.getProperty("test_pageNum")));
        queryVo.setPageSize(Integer.parseInt(env.getProperty("test_pageSize")));
        SiteCategory category = new SiteCategory();
        if(!StringUtils.isEmpty(env.getProperty("siteCategoryId"))){
        category.setCid(Integer.parseInt(env.getProperty("siteCategoryId")));
        }
        queryVo.setSiteCategory(category);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sites/list").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(queryVo))).andReturn();
        assertFun(mvcResult);
    }


    private void assertFun(MvcResult mvcResult) throws Exception{
        Assert.assertEquals(mvcResult.getResponse().getStatus(),200);
        Assert.assertEquals(JSON.parseObject(mvcResult.getResponse().getContentAsString(), Result.class).getCode(), ResultCode.SUCCESS.code());
    }
}
