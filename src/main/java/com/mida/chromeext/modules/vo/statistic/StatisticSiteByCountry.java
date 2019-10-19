package com.mida.chromeext.modules.vo.statistic;

import com.mida.chromeext.modules.pojo.Country;

/**
 * @author lihaoyu
 * @date 2019/10/19 20:57
 */
public class StatisticSiteByCountry extends Country{

    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
