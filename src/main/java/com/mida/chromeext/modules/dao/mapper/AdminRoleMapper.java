package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.AdminRole;
import com.mida.chromeext.modules.pojo.AdminRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {
    long countByExample(AdminRoleExample example);

    int deleteByExample(AdminRoleExample example);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    List<AdminRole> selectByExample(AdminRoleExample example);

    int updateByExampleSelective(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);

    int updateByExample(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);
}