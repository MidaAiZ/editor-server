package com.mida.chromeext.modules.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SiteTracker implements Serializable {
    private Integer tid;

    private String countryCode;

    private String trackers;

    private static final long serialVersionUID = 1L;

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
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getTrackers() {
        return trackers;
    }

    public void setTrackers(String trackers) {
        this.trackers = trackers == null ? null : trackers.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", trackers=").append(trackers);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}