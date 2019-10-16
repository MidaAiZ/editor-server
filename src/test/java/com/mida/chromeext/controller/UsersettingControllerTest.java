package com.mida.chromeext.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.ChromeExtApplication;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;

import javax.servlet.http.Cookie;


/**
 * @author lihaoyu
 * @date 2019/10/13 16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test/test-params-usersetting.properties"})
public class UsersettingControllerTest {

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
        profileTest();

        defaultTest();
    }

    public void profileTest() throws Exception{
        Cookie cookie = userLogin();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user_settings/profile").cookie(cookie)
                ).andReturn();
        assertFun(mvcResult);
    }

    public void defaultTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user_settings/default")
        ).andReturn();
        assertFun(mvcResult);
    }



    public Cookie userLogin() throws Exception {
        String email = env.getProperty("email");
        String password = env.getProperty("password");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login").
                contentType(MediaType.APPLICATION_FORM_URLENCODED).param("password",password)
                .param("email",email)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Cookie token = response.getCookie("token");
        return token;
    }

    private void assertFun(MvcResult mvcResult) throws Exception{
        Assert.assertEquals(mvcResult.getResponse().getStatus(),200);
        Assert.assertEquals(JSON.parseObject(mvcResult.getResponse().getContentAsString(), Result.class).getCode(), ResultCode.SUCCESS.code());
    }
}
