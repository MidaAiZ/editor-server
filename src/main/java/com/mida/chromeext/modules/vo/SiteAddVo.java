package com.mida.chromeext.modules.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 管理员添加网站 Vo
 *
 * @author lihaoyu
 * @date 2019/9/28 21:17
 */
public class SiteAddVo {

    @NotBlank
    @ApiModelProperty("网站标题")
    private String title;

    @NotBlank
    @ApiModelProperty("网站链接")
    private String url;

    @NotBlank
    @ApiModelProperty("网站图标")
    private String icon;

    @NotNull
    @ApiModelProperty("网站分类ID")
    private Integer cateId;

    @Size(min = 1)
    @ApiModelProperty("网站适用的国家码列表")
    private List<String> countryCodes;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(List<String> countryCodes) {
        this.countryCodes = countryCodes;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }
}