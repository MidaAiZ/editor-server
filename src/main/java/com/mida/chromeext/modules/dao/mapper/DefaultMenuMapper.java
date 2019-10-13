package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.DefaultMenu;
import com.mida.chromeext.modules.pojo.DefaultMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DefaultMenuMapper {
    long countByExample(DefaultMenuExample example);

    int deleteByExample(DefaultMenuExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(DefaultMenu record);

    int insertSelective(DefaultMenu record);

    List<DefaultMenu> selectByExampleWithBLOBs(DefaultMenuExample example);

    List<DefaultMenu> selectByExample(DefaultMenuExample example);

    DefaultMenu selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") DefaultMenu record, @Param("example") DefaultMenuExample example);

    int updateByExampleWithBLOBs(@Param("record") DefaultMenu record, @Param("example") DefaultMenuExample example);

    int updateByExample(@Param("record") DefaultMenu record, @Param("example") DefaultMenuExample example);

    int updateByPrimaryKeySelective(DefaultMenu record);

    int updateByPrimaryKeyWithBLOBs(DefaultMenu record);

    int updateByPrimaryKey(DefaultMenu record);
}