package com.mida.chromeext.pojos;

public class Countries {

  private long cid;
  private String name;
  private long usersCount;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getUsersCount() {
    return usersCount;
  }

  public void setUsersCount(long usersCount) {
    this.usersCount = usersCount;
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
