package net.tabplus.api.modules.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class SiteViewHistory implements Serializable {
    @ApiModelProperty(hidden = true)
    private String hid;
    @ApiModelProperty(hidden = true)
    private String ip;
    @ApiModelProperty(hidden = true)
    private Integer userId;
    @ApiModelProperty("被浏览网站的id，如果有的话上传")
    private Integer siteId;
    @ApiModelProperty("被浏览网站的title，如果有的话上传")
    private String siteTitle;
    @ApiModelProperty(value = "被浏览网站的URL，必填", required = true)
    @NotBlank
    private String siteUrl;
    @ApiModelProperty(hidden = true)
    private String browserUa;
    @ApiModelProperty(hidden = true)
    private String countryCode;
    @ApiModelProperty(hidden = true)
    private Date createdAt;
    @ApiModelProperty(hidden = true)
    private String pluginPlatform;
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

    public String getBrowserUa() {
        return browserUa;
    }

    public void setBrowserUa(String browserUa) {
        this.browserUa = browserUa == null ? null : browserUa.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPluginPlatform() {
        return pluginPlatform;
    }

    public void setPluginPlatform(String pluginPlatform) {
        this.pluginPlatform = pluginPlatform == null ? null : pluginPlatform.trim();
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
        sb.append(", browserUa=").append(browserUa);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", pluginPlatform=").append(pluginPlatform);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}