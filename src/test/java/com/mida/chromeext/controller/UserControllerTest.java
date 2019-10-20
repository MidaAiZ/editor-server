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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

/**
 * @author lihaoyu
 * @date 2019/10/12 13:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test/test-params-user.properties"})
public class UserControllerTest {
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
    public void userTest() throws Exception {
        // 用户注册
        registerTest();

        // 用户登录
        Cookie token = userLogin();

        // 获取用户信息
        profileTest(token);

        // 改密码
        changePwdTest(token);

        // 更新用户信息
        updateInfoTest(token);
    }

    public void updateInfoTest(Cookie token) throws Exception {
        String tel = env.getProperty("tel");
        String gender = env.getProperty("gender");
        String occupation = env.getProperty("occupation");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/profile").cookie(token)
                .param("tel", tel).param("gender", gender).param("occupation", occupation)).andReturn();
        assertFun(mvcResult);
    }

    public void changePwdTest(Cookie token) throws Exception {
        String oldPwd = env.getProperty("oldPwd");
        String newPwd = env.getProperty("newPwd");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/password").cookie(token)
                .param("oldPwd", oldPwd).param("newPwd", newPwd)).andReturn();
        assertFun(mvcResult);
    }

    public void profileTest(Cookie token) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/profile").cookie(token)).andReturn();
        assertFun(mvcResult);
    }

    public void registerTest() throws Exception {
        String random = String.valueOf((int) (Math.random() * 10000));
        String email = random + "@qq.com";
        String password = random + "Francis";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .param("number", random).param("email", email).param("password", password)).andReturn();
        assertFun(mvcResult);
    }

    public Cookie userLogin() throws Exception {
        String email = env.getProperty("email");
        String password = env.getProperty("password");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login").
                contentType(MediaType.APPLICATION_FORM_URLENCODED).param("password", password)
                .param("email", email)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Cookie token = response.getCookie("token");
        return token;
    }

    private void assertFun(MvcResult mvcResult) throws Exception {
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assert.assertEquals(JSON.parseObject(mvcResult.getResponse().getContentAsString(), Result.class).getCode(), ResultCode.SUCCESS.code());
    }

}
