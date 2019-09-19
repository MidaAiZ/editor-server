package com.mida.chromeext.pojo;

import java.io.Serializable;
import java.util.Date;

public class Site implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.sid
     *
     * @mbggenerated
     */
    private Integer sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.used_count
     *
     * @mbggenerated
     */
    private Integer usedCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.page_views
     *
     * @mbggenerated
     */
    private Integer pageViews;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.weight
     *
     * @mbggenerated
     */
    private Float weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.created_by
     *
     * @mbggenerated
     */
    private Integer createdBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.created_at
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sites.updated_at
     *
     * @mbggenerated
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sites
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.sid
     *
     * @return the value of sites.sid
     *
     * @mbggenerated
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.sid
     *
     * @param sid the value for sites.sid
     *
     * @mbggenerated
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.title
     *
     * @return the value of sites.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.title
     *
     * @param title the value for sites.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.url
     *
     * @return the value of sites.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.url
     *
     * @param url the value for sites.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.icon
     *
     * @return the value of sites.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.icon
     *
     * @param icon the value for sites.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.used_count
     *
     * @return the value of sites.used_count
     *
     * @mbggenerated
     */
    public Integer getUsedCount() {
        return usedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.used_count
     *
     * @param usedCount the value for sites.used_count
     *
     * @mbggenerated
     */
    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.page_views
     *
     * @return the value of sites.page_views
     *
     * @mbggenerated
     */
    public Integer getPageViews() {
        return pageViews;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.page_views
     *
     * @param pageViews the value for sites.page_views
     *
     * @mbggenerated
     */
    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.weight
     *
     * @return the value of sites.weight
     *
     * @mbggenerated
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.weight
     *
     * @param weight the value for sites.weight
     *
     * @mbggenerated
     */
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.created_by
     *
     * @return the value of sites.created_by
     *
     * @mbggenerated
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.created_by
     *
     * @param createdBy the value for sites.created_by
     *
     * @mbggenerated
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.created_at
     *
     * @return the value of sites.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.created_at
     *
     * @param createdAt the value for sites.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sites.updated_at
     *
     * @return the value of sites.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sites.updated_at
     *
     * @param updatedAt the value for sites.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sites
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", usedCount=").append(usedCount);
        sb.append(", pageViews=").append(pageViews);
        sb.append(", weight=").append(weight);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}