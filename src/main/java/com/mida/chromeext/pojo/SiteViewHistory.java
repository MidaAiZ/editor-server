package com.mida.chromeext.pojo;

import java.io.Serializable;
import java.util.Date;

public class SiteViewHistory implements Serializable {
    private String hid;

    private String ip;

    private Integer userId;

    private Integer siteId;

    private String siteTitle;

    private String siteUrl;

    private Byte times;

    private String loc;

    private Date createdAt;

    private Date lastViewTime;

    private byte[] browserUa;

    private static final long serialVersionUID = 1L;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid == null ? null : hid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle == null ? null : siteTitle.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc == null ? null : loc.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Date lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    public byte[] getBrowserUa() {
        return browserUa;
    }

    public void setBrowserUa(byte[] browserUa) {
        this.browserUa = browserUa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hid=").append(hid);
        sb.append(", ip=").append(ip);
        sb.append(", userId=").append(userId);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteTitle=").append(siteTitle);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", times=").append(times);
        sb.append(", loc=").append(loc);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lastViewTime=").append(lastViewTime);
        sb.append(", browserUa=").append(browserUa);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}