package com.mida.chromeext.pojo;

import java.io.Serializable;

public class UserSite implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users_sites.user_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users_sites.site_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    private Integer siteId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table users_sites
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users_sites.user_id
     *
     * @return the value of users_sites.user_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users_sites.user_id
     *
     * @param userId the value for users_sites.user_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users_sites.site_id
     *
     * @return the value of users_sites.site_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users_sites.site_id
     *
     * @param siteId the value for users_sites.site_id
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_sites
     *
     * @mbggenerated Tue Sep 17 16:08:46 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", siteId=").append(siteId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}