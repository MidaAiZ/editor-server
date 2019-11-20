package net.tabplus.api.modules.vo.statistic;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author lihaoyu
 * @date 2019/10/19 16:04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticCountVo {

    /***
     * 查询结果中Map对应的键值
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
