package com.mida.chromeext.modules.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "国家(地区)主键", hidden = true)
    private Integer cid;
    @NotNull(message = "Name can not be null")
    @ApiModelProperty("国家(地区)的英文名字")
    private String name;
    @ApiModelProperty("国家(地区)的本地语言名字")
    private String localeName;
    @NotNull(message = "Code can not be null")
    @ApiModelProperty("唯一国家(地区)码")
    private String code;
    @ApiModelProperty("区域电话前缀,如中国大陆是86")
    private String telPrefix;
    @ApiModelProperty("区域时区")
    private String timeZone;
    @ApiModelProperty("用来标识该记录是国家还是区域，0表示国家，1表示区域，默认0")
    private Short type;
    private Integer usersCount;
    private Date createdAt;
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

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName == null ? null : localeName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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
        sb.append(", localeName=").append(localeName);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
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