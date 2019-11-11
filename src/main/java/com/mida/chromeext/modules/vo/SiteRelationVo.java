package com.mida.chromeext.modules.vo;

import com.mida.chromeext.modules.pojo.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


public class SiteRelationVo extends Site {
    @ApiModelProperty("创建网站的管理员")
    private Admin createdAdmin;
    @ApiModelProperty("创建网站的用户")
    private User createdUser;
    @ApiModelProperty("网站所属分类")
    private SiteCategory category;
    @ApiModelProperty("网站所关联的国家（地区）列表")
    private List<Country> countryList;
    @ApiModelProperty("网站所关联的国家（地区）数量")
    private int countriesCount;

    public Admin getCreatedAdmin() {
        return createdAdmin;
    }

    public void setCreatedAdmin(Admin createdAdmin) {
        this.createdAdmin = createdAdmin;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public SiteCategory getCategory() {
        return category;
    }

    public void setCategory(SiteCategory category) {
        this.category = category;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public int getCountriesCount() {
        return countriesCount;
    }

    public void setCountriesCount(int countriesCount) {
        this.countriesCount = countriesCount;
    }
}
