package com.mida.chromeext.modules.vo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lihaoyu
 * @date 2019/9/24 18:52
 */
public class SiteListQueryVo extends ListQueryVo {

    private String keyword;

    private List<Integer> categoryIds;

    private ArrayList<String> countryCodes; // = Lists.newArrayList(Constant.THE_WORLD);

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public ArrayList<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(ArrayList<String> countryCodes) {
        this.countryCodes = countryCodes;
    }
}
