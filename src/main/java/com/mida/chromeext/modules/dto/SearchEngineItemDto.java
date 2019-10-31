package com.mida.chromeext.modules.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel
public class SearchEngineItemDto implements Serializable {
    @NotNull(message = "urlList不能为空")
    @Size(min = 1)
    @Valid
    private List<SearchEnginUrlMapDto> urlList;
    @NotNull(message = "title不允许为空")
    @NotBlank(message = "title不允许为空")
    private String title;
    @NotNull(message = "iconSrc不允许为空")
    @NotBlank(message = "iconSrc不允许为空")
    private String iconSrc;

    public List<SearchEnginUrlMapDto> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<SearchEnginUrlMapDto> urlList) {
        this.urlList = urlList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }
}
