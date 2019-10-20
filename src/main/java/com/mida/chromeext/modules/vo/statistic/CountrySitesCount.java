package com.mida.chromeext.modules.vo.statistic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mida.chromeext.modules.pojo.Country;

/**
 * @author lihaoyu
 * @date 2019/10/19 20:57
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountrySitesCount extends Country {

    private Long sitesCount;

    public Long getSitesCount() {
        return sitesCount;
    }

    public void setSitesCount(Long sitesCount) {
        this.sitesCount = sitesCount;
    }
}
