package com.mida.chromeext.vo;

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
    private String title;

    @NotBlank
    private String url;

    @NotBlank
    private String icon;

    @NotNull
    private Integer cateId;

    @Size(min = 1)
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
