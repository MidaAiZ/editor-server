package com.mida.chromeext.controller;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.ChromeExtApplication;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author lihaoyu
 * @date 2019/10/13 16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test/test-params-engine.properties"})
public class EngineControllerTest {

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
        defaultTest();
    }

    public void defaultTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search-engines/default")
                .param("code", env.getProperty("code"))).andReturn();
        assertFun(mvcResult);
    }


    private void assertFun(MvcResult mvcResult) throws Exception {
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assert.assertEquals(JSON.parseObject(mvcResult.getResponse().getContentAsString(), Result.class).getCode(), ResultCode.SUCCESS.code());
    }
}
