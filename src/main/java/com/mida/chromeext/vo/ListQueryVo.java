package com.mida.chromeext.vo;

import javax.validation.constraints.Min;

/**
 * @author lihaoyu
 * @date 2019/9/26 19:05
 */
public class ListQueryVo {

    @Min(0)
    private Integer pageNum;

    @Min(1)
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
