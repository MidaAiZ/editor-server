package com.mida.chromeext.modules.vo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class ListResultVo<T> implements Serializable {

    @ApiModelProperty("目标数据集合")
    private List<T> list;

    @ApiModelProperty("数据总量")
    private long totalCount;
    @ApiModelProperty("当前页数")
    private long currentPage;
    @ApiModelProperty("每页数据量")
    private long pageSize;
    @ApiModelProperty("总页数")
    private long pageCount;

    public ListResultVo(Page<T> page) {
        list = page.getResult();
        totalCount = page.getTotal();
        pageSize = page.getPageSize() <= 0 ? totalCount : page.getPageSize();
        pageCount = (long) Math.ceil(totalCount / (float) pageSize);
        currentPage = Math.max(1, page.getPageNum());
    }

    public ListResultVo(List<T> list) {
        this.list = list;
    }

    public ListResultVo(List<T> list, long totalCount, long currentPage, long pageSize, long pageCount) {
        this.list = list;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
