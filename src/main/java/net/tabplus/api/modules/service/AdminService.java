package net.tabplus.api.modules.service;

import com.github.pagehelper.PageHelper;
import net.tabplus.api.components.shiro.RoleConstant;
import net.tabplus.api.modules.dao.AdminDAO;
import net.tabplus.api.modules.dto.NewAdminDto;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.pojo.AdminExample;
import net.tabplus.api.modules.vo.ListQueryVo;
import net.tabplus.api.utils.NumConst;
import net.tabplus.api.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    RoleService roleService;
    @Autowired
    private AdminDAO adminDAO;

    /**
     * 创建管理员，同时指定其所拥有的角色
     *
     * @param newAdmin
     * @return
     */
    public Admin createAdmin(NewAdminDto newAdmin) {
        Admin admin = new Admin();
        admin.setSalt(UUID.randomUUID().toString());
        Date date = new Date();
        admin.setCreatedAt(date);
        admin.setUpdatedAt(date);
        admin.setPassword(newAdmin.getPassword());
        admin.setNumber(newAdmin.getNumber());
        admin.setEmail(newAdmin.getEmail());
        admin.setTel(newAdmin.getTel());
        admin.setTelPrefix(newAdmin.getTelPrefix());
        admin.setPassword(ShiroUtils.EncodeSalt(admin.getPassword(), admin.getSalt()));
        if (adminDAO.insertSelective(admin) > 0) {
            roleService.addRolesToAdmin(admin.getAid(), newAdmin.getRoleIds());
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
     *
     * @param number
     * @return
     */
    public Admin getAdminByNumber(String number) {
        return adminDAO.selectAdminByNumber(number);
    }

    /**
     * 获取管理员列表
     */
    public List<Admin> getAdminList(ListQueryVo queryVo) {
        PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize(), false);
        AdminExample ex = new AdminExample();
        return adminDAO.selectByExample(ex);
    }

    /**
     * 根据角色列表获取管理员
     */
    public List<Admin> getAdminListByRoleNames(ListQueryVo queryVo, List<String> roleNames) {
        PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize(), false);
        return adminDAO.selectAdminByRoleNames(roleNames);
    }

    /**
     * 根据角色获取管理员
     */
    public Admin getAdminByRoleName(String roleName) {
        return adminDAO.selectAdminByRoleName(roleName);
    }

    /**
     * 获取系统的第一个超级管理员
     * 该管理员理论上不可删除，不可变更
     *
     * @return
     */
    public Admin getFirstSuperAdmin() {
        PageHelper.startPage(1, 1, false);
        List<String> superName = new ArrayList<>(1);
        superName.add(RoleConstant.SUPER_ROLE);
        List<Admin> list = adminDAO.selectAdminByRoleNames(superName);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
