package com.mida.chromeext.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
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

    @Size(min = 1)
    private List<Integer> countryIds;

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

    public List<Integer> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(List<Integer> countryIds) {
        this.countryIds = countryIds;
    }
}
