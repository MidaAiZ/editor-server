package com.mida.chromeext.modules.controller.app;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
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

import java.io.UnsupportedEncodingException;

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

        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/users/profile").cookie(token)).andReturn();
        System.out.println(mvcResult1.getResponse().getContentAsString());
        System.out.println("————————");


        String oldPwd = env.getProperty("user.oldPwd");
        String newPwd = env.getProperty("user.newPwd");
        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post("/users/pwd-reset").cookie(token)
        .param("oldPwd",oldPwd).param("newPwd",newPwd)).andReturn();
        System.out.println(mvcResult2.getResponse().getContentAsString());
        System.out.println("————————");

        String tel = env.getProperty("user.tel");
        String gender = env.getProperty("user.gender");
        String occupation = env.getProperty("user.occupation");
        MvcResult mvcResult3 = mockMvc.perform(MockMvcRequestBuilders.post("/users/change-info").cookie(token)
                .param("tel",tel).param("gender",gender).param("occupation",occupation)).andReturn();
        System.out.println(mvcResult3.getResponse().getContentAsString());
        System.out.println("————————");


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
