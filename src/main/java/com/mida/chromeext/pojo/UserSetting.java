package com.mida.chromeext.pojo;

import java.io.Serializable;

public class UserSetting implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_settings.sid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    private Integer sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_settings.uid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_settings.settings
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    private byte[] settings;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_settings
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_settings.sid
     *
     * @return the value of user_settings.sid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_settings.sid
     *
     * @param sid the value for user_settings.sid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_settings.uid
     *
     * @return the value of user_settings.uid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_settings.uid
     *
     * @param uid the value for user_settings.uid
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_settings.settings
     *
     * @return the value of user_settings.settings
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public byte[] getSettings() {
        return settings;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_settings.settings
     *
     * @param settings the value for user_settings.settings
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    public void setSettings(byte[] settings) {
        this.settings = settings;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_settings
     *
     * @mbggenerated Sun Sep 15 20:45:43 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", uid=").append(uid);
        sb.append(", settings=").append(settings);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}