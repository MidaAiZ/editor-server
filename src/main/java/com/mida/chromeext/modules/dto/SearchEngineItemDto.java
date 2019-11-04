package com.mida.chromeext.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jdom.IllegalDataException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;

@ApiModel
public class SearchEngineItemDto implements Serializable {
    public static final String WebUrl = "web";
    public static final String ImageUrl = "image";
    public static final String NewsUrl = "news";
    public static final String VideoUrl = "video";
    public static final String MapUrl = "map";

    @NotNull(message = "urls不能为空")
    @ApiModelProperty("搜索类型，网站、图片、视频等，支持web、image、news、video、map五种类型")
    private HashMap urls;
    @NotNull(message = "title不允许为空")
    @NotBlank(message = "title不允许为空")
    private String title;
    @NotNull(message = "iconSrc不允许为空")
    @NotBlank(message = "iconSrc不允许为空")
    private String iconSrc;


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

    public HashMap getUrls() {
        return urls;
    }

    public void setUrls(HashMap<String, String> urls) {
        if (!urls.containsKey(WebUrl)) {
            throw new IllegalDataException("urls中web类型的url不能为空");
        }
        String regex = "^(" + WebUrl + "|" + ImageUrl + "|" + NewsUrl + "|" + VideoUrl + "|" + MapUrl + ")$";
        for (String key : urls.keySet()) {
            if (!key.matches(regex)) {
                throw new IllegalDataException("urls包含不支持的key值, 仅支持满足以下正则的key值：" + regex);
            }
        }
        this.urls = urls;
    }
}
