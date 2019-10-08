package com.mida.chromeext.dto;

import java.io.Serializable;

/**
 * @author lihaoyu
 * @date 2019/10/7 20:26
 */
public class EngineDto implements Serializable {

    private String icon;

    private String url;

    private String name;

    private String invoke;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }
}