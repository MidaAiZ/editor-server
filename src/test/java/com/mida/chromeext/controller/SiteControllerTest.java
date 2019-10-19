package com.mida.chromeext.controller;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        if(StringUtils.isNotBlank(env.getProperty("keyWord"))){
            map.add("keyWord",env.getProperty("keyWord"));
        }
        if(StringUtils.isNotBlank(env.getProperty("countryCodeList"))){
            map.add("keyWord",env.getProperty("countryCodeList"));
        }
        if(StringUtils.isNotBlank(env.getProperty("categoryIdList"))){
            map.add("keyWord",env.getProperty("categoryIdList"));
        }
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/sites").
                    params(map)
                    .param("pageNum", env.getProperty("test_pageNum"))
                    .param("pageSize", env.getProperty("test_pageSize"))
                ).andReturn();
        assertFun(mvcResult);
    }


    private void assertFun(MvcResult mvcResult) throws Exception{
        Assert.assertEquals(mvcResult.getResponse().getStatus(),200);
        Assert.assertEquals(JSON.parseObject(mvcResult.getResponse().getContentAsString(), Result.class).getCode(), ResultCode.SUCCESS.code());
      }
}
