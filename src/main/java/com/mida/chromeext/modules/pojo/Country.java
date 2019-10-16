package com.mida.chromeext.modules.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "国家(地区)主键", hidden = true)
    private Integer cid;

    @NotNull(message = "Name can not be null")
    @ApiModelProperty("国家(地区)名字")
    private String name;

    @NotNull(message = "Code can not be null")
    @ApiModelProperty("唯一国家(地区)码")
    private String code;

    @ApiModelProperty("区域电话前缀,如中国大陆是86")
    private String telPrefix;

    @ApiModelProperty("区域时区")
    private String timeZone;

    @JsonIgnore
    private Integer usersCount;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix == null ? null : telPrefix.trim();
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
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
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", telPrefix=").append(telPrefix);
        sb.append(", timeZone=").append(timeZone);
        sb.append(", usersCount=").append(usersCount);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}