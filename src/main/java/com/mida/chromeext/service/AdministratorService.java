package com.mida.chromeext.service;

import com.mida.chromeext.mapper.AdminMapper;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lihaoyu
 * @date 2019/9/15 16:11
 */

@Service
public class AdministratorService {

    @Autowired
    AdminMapper adminsMapper;

    public String login(String userName, String passWord){
        AdminExample example = new AdminExample();
        example.createCriteria().andNumberEqualTo(userName).andPwdEqualTo(passWord);
        List<Admin> admins = adminsMapper.selectByExample(example);



        return "";
    }

}
