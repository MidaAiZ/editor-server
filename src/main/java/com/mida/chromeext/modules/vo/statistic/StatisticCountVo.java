package com.mida.chromeext.modules.vo.statistic;

/**
 * @author lihaoyu
 * @date 2019/10/19 16:04
 */
public class StatisticCountVo {

    /***
     * 可能是日，月
     */
    private String name;

    private Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
