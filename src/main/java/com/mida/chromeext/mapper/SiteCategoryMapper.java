package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.SiteCategory;
import com.mida.chromeext.pojo.SiteCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteCategoryMapper {
    int countByExample(SiteCategoryExample example);

    int deleteByExample(SiteCategoryExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(SiteCategory record);

    int insertSelective(SiteCategory record);

    List<SiteCategory> selectByExample(SiteCategoryExample example);

    SiteCategory selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") SiteCategory record, @Param("example") SiteCategoryExample example);

    int updateByExample(@Param("record") SiteCategory record, @Param("example") SiteCategoryExample example);

    int updateByPrimaryKeySelective(SiteCategory record);

    int updateByPrimaryKey(SiteCategory record);
}