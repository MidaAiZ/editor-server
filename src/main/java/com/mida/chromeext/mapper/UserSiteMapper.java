package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.UserSite;
import com.mida.chromeext.pojo.UserSiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSiteMapper {
    int countByExample(UserSiteExample example);

    int deleteByExample(UserSiteExample example);

    int insert(UserSite record);

    int insertSelective(UserSite record);

    List<UserSite> selectByExample(UserSiteExample example);

    int updateByExampleSelective(@Param("record") UserSite record, @Param("example") UserSiteExample example);

    int updateByExample(@Param("record") UserSite record, @Param("example") UserSiteExample example);
}