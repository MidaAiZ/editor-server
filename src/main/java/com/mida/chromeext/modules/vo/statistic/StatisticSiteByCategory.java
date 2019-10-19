package com.mida.chromeext.modules.vo.statistic;

import com.mida.chromeext.modules.pojo.SiteCategory;

/**
 * @author lihaoyu
 * @date 2019/10/19 21:14
 */
public class StatisticSiteByCategory extends SiteCategory{

    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
