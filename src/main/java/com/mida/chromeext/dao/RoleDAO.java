package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.RoleMapper;
import com.mida.chromeext.pojo.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface RoleDAO extends RoleMapper {
    List<Role> getRolesWithPermissions();
}
