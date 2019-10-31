package com.mida.chromeext.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel
public class SearchEnginUrlMapDto implements Serializable {
    @ApiModelProperty("搜索类型，网站、图片、视频等，支持web、image、news、video、map五种类型")
    @NotBlank(message = "type不能为空")
    @Pattern(regexp = "^(web|image|news|video|map)$")
    private String type;
    @ApiModelProperty("搜索类型对应的url")
    @NotBlank(message = "url不能为空")
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
