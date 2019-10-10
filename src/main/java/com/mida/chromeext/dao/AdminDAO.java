package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.AdminMapper;
import com.mida.chromeext.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 19:59
 */
@Repository
public interface AdminDAO extends AdminMapper {
    int deleteRoleByAid(int aid, int rid);
    int deleteAllRolesByAid(int aid);
    int addRoleByAid(int aid, int rid);
    List<Role> getRolesByAid(int aid);
}
