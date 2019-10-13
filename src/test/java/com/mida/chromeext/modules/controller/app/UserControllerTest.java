package com.mida.chromeext.modules.controller.app;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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

import com.mida.chromeext.ChromeExtApplication;

/**
 * @author lihaoyu
 * @date 2019/10/12 13:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChromeExtApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:test-params.properties"})
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
        Cookie token = userLogin();

        profileTest(token);

        changePwdTest(token);

        updateInfoTest(token);
    }

    public void updateInfoTest(Cookie token) throws Exception{
        String tel = env.getProperty("user.tel");
        String gender = env.getProperty("user.gender");
        String occupation = env.getProperty("user.occupation");
        MvcResult mvcResult3 = mockMvc.perform(MockMvcRequestBuilders.put("/users/profile").cookie(token)
                .param("tel",tel).param("gender",gender).param("occupation",occupation)).andReturn();
        Assert.assertEquals(mvcResult3.getResponse().getStatus(),200);
    }

    public void changePwdTest(Cookie token) throws Exception{
        String oldPwd = env.getProperty("user.oldPwd");
        String newPwd = env.getProperty("user.newPwd");
        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.put("/users/password").cookie(token)
                .param("oldPwd",oldPwd).param("newPwd",newPwd)).andReturn();
        Assert.assertEquals(mvcResult2.getResponse().getStatus(),200);
    }

    public void profileTest(Cookie token) throws Exception{
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/users/profile").cookie(token)).andReturn();
        Assert.assertEquals(mvcResult1.getResponse().getStatus(),200);
    }


    public Cookie userLogin() throws Exception {
        String email = env.getProperty("user.email");
        String password = env.getProperty("user.password");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login").
                contentType(MediaType.APPLICATION_FORM_URLENCODED).param("password",password)
                .param("email",email)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Cookie token = response.getCookie("token");
//        String content = response.getContentAsString();
////        System.out.println(content);
////        System.out.println("————————");
        return token;
    }


}
