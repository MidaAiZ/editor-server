package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.UserMenu;
import com.mida.chromeext.modules.pojo.UserMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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