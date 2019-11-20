package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.UserMenuExample;
import net.tabplus.api.modules.pojo.UserMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMenuMapper {
    long countByExample(UserMenuExample example);

    int deleteByExample(UserMenuExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(UserMenu record);

    int insertSelective(UserMenu record);

    List<UserMenu> selectByExampleWithBLOBs(UserMenuExample example);

    List<UserMenu> selectByExample(UserMenuExample example);

    UserMenu selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") UserMenu record, @Param("example") UserMenuExample example);

    int updateByExampleWithBLOBs(@Param("record") UserMenu record, @Param("example") UserMenuExample example);

    int updateByExample(@Param("record") UserMenu record, @Param("example") UserMenuExample example);

    int updateByPrimaryKeySelective(UserMenu record);

    int updateByPrimaryKeyWithBLOBs(UserMenu record);

    int updateByPrimaryKey(UserMenu record);
}