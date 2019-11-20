package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.AdminRole;
import net.tabplus.api.modules.pojo.AdminRoleExample;
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