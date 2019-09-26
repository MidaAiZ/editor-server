package com.mida.chromeext.vo;

import com.mida.chromeext.pojo.SiteCategory;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:52
 */
public class SiteListQueryVo extends ListQueryVo{

    private String keyWord;

    private SiteCategory siteCategory;

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

}
