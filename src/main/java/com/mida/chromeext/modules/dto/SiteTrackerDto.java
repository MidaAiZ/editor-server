package com.mida.chromeext.modules.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class SiteTrackerDto {
    @ApiModelProperty("记录主键")
    private Integer tid;

    @ApiModelProperty("区域码")
    private String countryCode;

    @ApiModelProperty("追踪器键值列表")
    private List<SiteTrackerItemDto> trackers;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<SiteTrackerItemDto> getTrackers() {
        return trackers;
    }

    public void setTrackers(List<SiteTrackerItemDto> trackers) {
        this.trackers = trackers;
    }
}
