package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.SiteViewHistory;
import com.mida.chromeext.pojo.SiteViewHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteViewHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int countByExample(SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int deleteByExample(SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String hid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int insert(SiteViewHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int insertSelective(SiteViewHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    List<SiteViewHistory> selectByExampleWithBLOBs(SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    List<SiteViewHistory> selectByExample(SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    SiteViewHistory selectByPrimaryKey(String hid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SiteViewHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SiteViewHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table site_view_histories
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SiteViewHistory record);
}