package net.tabplus.api.modules.pojo;

import java.io.Serializable;
import java.util.Date;

public class LoginRecord implements Serializable {
    private Long rid;

    private Integer uid;

    private String clientId;

    private String ip;

    private String ua;

    private String pluginPlatform;

    private String countryCode;

    private Date loginTime;

    private static final long serialVersionUID = 1L;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    public String getPluginPlatform() {
        return pluginPlatform;
    }

    public void setPluginPlatform(String pluginPlatform) {
        this.pluginPlatform = pluginPlatform == null ? null : pluginPlatform.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rid=").append(rid);
        sb.append(", uid=").append(uid);
        sb.append(", clientId=").append(clientId);
        sb.append(", ip=").append(ip);
        sb.append(", ua=").append(ua);
        sb.append(", pluginPlatform=").append(pluginPlatform);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}