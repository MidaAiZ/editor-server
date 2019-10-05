package com.mida.chromeext.vo;

/**
 * @author lihaoyu
 * @date 2019/9/26 19:05
 */
public class ListQueryVo {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

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
