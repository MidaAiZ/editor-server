package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.UsersSites;
import java.util.List;

public interface UsersSitesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated
     */
    int insert(UsersSites record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated
     */
    List<UsersSites> selectAll();
}