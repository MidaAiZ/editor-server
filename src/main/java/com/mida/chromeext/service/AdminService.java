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
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    /**
     * 根据用户名查询管理员
     *
     * @param userName 用户名
     * @return 管理员po或者null
     * @author lihaoyu
     * @date 2019/9/17 12:03
     */
    public Admin getAdminByNameAndPwd(String userName){
        AdminExample example = new AdminExample();
        example.createCriteria().andNumberEqualTo(userName);
        List<Admin> admins = adminDAO.selectByExample(example);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(NumConst.NUM0);
    }


}
