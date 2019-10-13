package com.mida.chromeext.modules.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class SiteCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cid;
    @NotBlank
    private String title;
    private Integer index;
    private Integer sitesCount;
    private Date createdAt;
    private Date updatedAt;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSitesCount() {
        return sitesCount;
    }

    public void setSitesCount(Integer sitesCount) {
        this.sitesCount = sitesCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", title=").append(title);
        sb.append(", index=").append(index);
        sb.append(", sitesCount=").append(sitesCount);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}