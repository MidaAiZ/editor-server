package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.UserSetting;
import com.mida.chromeext.modules.pojo.UserSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSettingMapper {
    long countByExample(UserSettingExample example);

    int deleteByExample(UserSettingExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(UserSetting record);

    int insertSelective(UserSetting record);

    List<UserSetting> selectByExampleWithBLOBs(UserSettingExample example);

    List<UserSetting> selectByExample(UserSettingExample example);

    UserSetting selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") UserSetting record, @Param("example") UserSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") UserSetting record, @Param("example") UserSettingExample example);

    int updateByExample(@Param("record") UserSetting record, @Param("example") UserSettingExample example);

    int updateByPrimaryKeySelective(UserSetting record);

    int updateByPrimaryKeyWithBLOBs(UserSetting record);

    int updateByPrimaryKey(UserSetting record);
}