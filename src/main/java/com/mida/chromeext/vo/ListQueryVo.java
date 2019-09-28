package com.mida.chromeext.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author lihaoyu
 * @date 2019/9/26 19:05
 */
public class ListQueryVo {

    @NotNull
    @Min(1)
    private Integer pageNum;

    @NotNull
    @Min(0)
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
