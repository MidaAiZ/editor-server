package com.mida.chromeext.service;

import com.mida.chromeext.dao.AdminDAO;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.AdminExample;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author lihaoyu
 * @date 2019/9/15 16:11
 */

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired RoleService roleService;

    /**
     * 创建管理员，同时指定其所拥有的角色
     * @param admin
     * @param roleIds
     * @return
     */
    public Admin createAdmin(Admin admin, List<Integer> roleIds) {
        admin.setSalt(UUID.randomUUID().toString());
        Date date = new Date();
        admin.setCreatedAt(date);
        admin.setUpdatedAt(date);
        admin.setPassword(ShiroUtils.EncodeSalt(admin.getPassword(), admin.getSalt()));
        if (adminDAO.insertSelective(admin) > 0) {
            roleService.addRolesToAdmin(admin.getAid(), roleIds);
            admin.setSalt(null);
            admin.setPassword(null);
            return admin;
        }
        return null;
    }

    /**
     * 根据用户名查询管理员
     *
     * @param userName 用户名
     * @return 管理员po或者null
     * @author lihaoyu
     * @date 2019/9/17 12:03
     */
    public Admin getAdminByNameAndPwd(String userName) {
        AdminExample example = new AdminExample();
        example.createCriteria().andNumberEqualTo(userName);
        List<Admin> admins = adminDAO.selectByExample(example);
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(NumConst.NUM0);
    }

    /**
     * 通过主键获取admin
     *
     * @param aid
     * @return
     */
    public Admin getAdminById(Integer aid) {
        return adminDAO.selectByPrimaryKey(aid);
    }

    /**
     * 根据number获取管理员
     * @param number
     * @return
     */
    public Admin getAdminByNumber(String number) {
        return adminDAO.selectAdminByNumber(number);
    }
}
