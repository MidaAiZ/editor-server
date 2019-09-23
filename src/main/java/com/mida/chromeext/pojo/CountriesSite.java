package com.mida.chromeext.pojo;

import java.io.Serializable;

public class CountriesSite implements Serializable {
    private static final long serialVersionUID = 1L;
    private Byte countryId;
    private Integer siteId;

    public Byte getCountryId() {
        return countryId;
    }

    public void setCountryId(Byte countryId) {
        this.countryId = countryId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countryId=").append(countryId);
        sb.append(", siteId=").append(siteId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}