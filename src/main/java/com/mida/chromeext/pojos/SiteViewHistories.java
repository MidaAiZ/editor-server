package com.mida.chromeext.pojos;

public class SiteViewHistories {

  private String hid;
  private String ip;
  private long userId;
  private long siteId;
  private String siteTitle;
  private String siteUrl;
  private String browserUa;
  private long times;
  private String loc;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp lastViewTime;


  public String getHid() {
    return hid;
  }

  public void setHid(String hid) {
    this.hid = hid;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getSiteId() {
    return siteId;
  }

  public void setSiteId(long siteId) {
    this.siteId = siteId;
  }


  public String getSiteTitle() {
    return siteTitle;
  }

  public void setSiteTitle(String siteTitle) {
    this.siteTitle = siteTitle;
  }


  public String getSiteUrl() {
    return siteUrl;
  }

  public void setSiteUrl(String siteUrl) {
    this.siteUrl = siteUrl;
  }


  public String getBrowserUa() {
    return browserUa;
  }

  public void setBrowserUa(String browserUa) {
    this.browserUa = browserUa;
  }


  public long getTimes() {
    return times;
  }

  public void setTimes(long times) {
    this.times = times;
  }


  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public java.sql.Timestamp getLastViewTime() {
    return lastViewTime;
  }

  public void setLastViewTime(java.sql.Timestamp lastViewTime) {
    this.lastViewTime = lastViewTime;
  }

}
