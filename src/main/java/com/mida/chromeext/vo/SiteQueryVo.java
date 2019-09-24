package com.mida.chromeext.vo;

import com.mida.chromeext.pojo.SiteCategory;

import javax.validation.constraints.Min;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:52
 */
public class SiteQueryVo {

    private String keyWord;

    private SiteCategory siteCategory;

    @Min(0)
    private Integer pageNum;

    @Min(1)
    private Integer pageSize;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public SiteCategory getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(SiteCategory siteCategory) {
        this.siteCategory = siteCategory;
    }

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
