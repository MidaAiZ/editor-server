package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.UserSite;
import com.mida.chromeext.pojo.UserSiteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSiteMapper {
    long countByExample(UserSiteExample example);

    int deleteByExample(UserSiteExample example);

    int insert(UserSite record);

    int insertSelective(UserSite record);

    List<UserSite> selectByExample(UserSiteExample example);

    int updateByExampleSelective(@Param("record") UserSite record, @Param("example") UserSiteExample example);

    int updateByExample(@Param("record") UserSite record, @Param("example") UserSiteExample example);
}