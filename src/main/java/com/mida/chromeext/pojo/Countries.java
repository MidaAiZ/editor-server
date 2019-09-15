package com.mida.chromeext.pojo;

import java.io.Serializable;
import java.util.Date;

public class Countries implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.cid
     *
     * @mbggenerated
     */
    private Byte cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.users_count
     *
     * @mbggenerated
     */
    private Integer usersCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.created_at
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column countries.updated_at
     *
     * @mbggenerated
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table countries
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.cid
     *
     * @return the value of countries.cid
     *
     * @mbggenerated
     */
    public Byte getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.cid
     *
     * @param cid the value for countries.cid
     *
     * @mbggenerated
     */
    public void setCid(Byte cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.name
     *
     * @return the value of countries.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.name
     *
     * @param name the value for countries.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.users_count
     *
     * @return the value of countries.users_count
     *
     * @mbggenerated
     */
    public Integer getUsersCount() {
        return usersCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.users_count
     *
     * @param usersCount the value for countries.users_count
     *
     * @mbggenerated
     */
    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.created_at
     *
     * @return the value of countries.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.created_at
     *
     * @param createdAt the value for countries.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column countries.updated_at
     *
     * @return the value of countries.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column countries.updated_at
     *
     * @param updatedAt the value for countries.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countries
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", name=").append(name);
        sb.append(", usersCount=").append(usersCount);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}