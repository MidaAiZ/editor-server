package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.RolePermissionMapper;
import com.mida.chromeext.pojo.Permission;
import com.mida.chromeext.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionDAO extends RolePermissionMapper {
}
