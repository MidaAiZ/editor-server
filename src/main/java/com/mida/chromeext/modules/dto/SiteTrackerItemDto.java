package com.mida.chromeext.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class SiteTrackerItemDto {
    @ApiModelProperty("键")
    @NotNull
    private String key;
    @ApiModelProperty("值")
    @NotNull
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}