package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.UserSetting;
import net.tabplus.api.modules.pojo.UserSettingExample;
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