package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.UserSite;
import com.mida.chromeext.pojo.UserSiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSiteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int countByExample(UserSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int deleteByExample(UserSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int insert(UserSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int insertSelective(UserSite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    List<UserSite> selectByExample(UserSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserSite record, @Param("example") UserSiteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    int updateByExample(@Param("record") UserSite record, @Param("example") UserSiteExample example);
}