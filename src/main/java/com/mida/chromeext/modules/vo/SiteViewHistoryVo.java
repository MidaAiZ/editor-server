package com.mida.chromeext.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "浏览记录查询对象")
public class SiteViewHistoryVo extends ListQueryVo {
    @ApiModelProperty(value = "创建记录日期>=，格式2019-01-01 00:00:00")
    private Date createdBefore;
    @ApiModelProperty(value = "创建记录日期<=，格式2019-01-01 00:00:00")
    private Date createdAfter;
    @ApiModelProperty(value = "网站URL，模糊查询")
    private String siteUrl;
    @ApiModelProperty(value = "网站名称，模糊查询")
    private String siteTitle;
    @ApiModelProperty(value = "网址列表，精准查询")
    private List<String> siteUrlList;
    @ApiModelProperty(value = "网站名称列表，精准查询")
    private List<String> siteTitleList;
    @ApiModelProperty(value = "网站ID列表，精准查询")
    private List<String> siteIdList;
    @ApiModelProperty(value = "用户ID列表，精准查询")
    private List<Integer> userIdList;
    @ApiModelProperty(value = "IP列表，精准查询")
    private List<String> ipList;

    public Date getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle;
    }

    public List<String> getSiteUrlList() {
        return siteUrlList;
    }

    public void setSiteUrlList(List<String> siteUrlList) {
        this.siteUrlList = siteUrlList;
    }

    public List<String> getSiteTitleList() {
        return siteTitleList;
    }

    public void setSiteTitleList(List<String> siteTitleList) {
        this.siteTitleList = siteTitleList;
    }

    public List<String> getSiteIdList() {
        return siteIdList;
    }

    public void setSiteIdList(List<String> siteIdList) {
        this.siteIdList = siteIdList;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }
}
