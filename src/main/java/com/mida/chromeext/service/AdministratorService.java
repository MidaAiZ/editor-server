package com.mida.chromeext.service;

import java.util.Collections;
import java.util.List;

import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.chromeext.dao.AdminDAO;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.AdminExample;


/**
 * @author lihaoyu
 * @date 2019/9/15 16:11
 */

@Service
public class AdministratorService {

    @Autowired
    private AdminDAO adminDAO;

    /**
     * 根据用户名密码查询管理员
     *
     * @param userName 用户名
     * @param passWord 密码，密文？
     * @return 管理员po或者null
     * @author lihaoyu
     * @date 2019/9/17 12:03
     */
    public Admin getAdminByNameAndPwd(String userName, String passWord){
        AdminExample example = new AdminExample();
        example.createCriteria().andNumberEqualTo(userName).andPwdEqualTo(passWord);
        List<Admin> admins = adminDAO.selectByExample(example);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(NumConst.NUM0);
    }

}
