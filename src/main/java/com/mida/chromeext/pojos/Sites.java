package com.mida.chromeext.pojos;


public class Sites {

  private long sid;
  private String title;
  private String url;
  private long usedCount;
  private long pageViews;
  private double weight;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


  public long getSid() {
    return sid;
  }

  public void setSid(long sid) {
    this.sid = sid;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getUsedCount() {
    return usedCount;
  }

  public void setUsedCount(long usedCount) {
    this.usedCount = usedCount;
  }


  public long getPageViews() {
    return pageViews;
  }

  public void setPageViews(long pageViews) {
    this.pageViews = pageViews;
  }


  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public java.sql.Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(java.sql.Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

}
