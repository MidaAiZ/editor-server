package com.mida.chromeext.modules.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.mida.chromeext.utils.Constant;


/**
 * @author lihaoyu
 * @date 2019/9/24 18:52
 */
public class SiteListQueryVo extends ListQueryVo {

    private String keyWord;

    private List<Integer> categoryIdList;

    private ArrayList<String> countryCodeList = Lists.newArrayList(Constant.THE_WORLD);

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<Integer> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Integer> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public ArrayList<String> getCountryCodeList() {
        return countryCodeList;
    }

    public void setCountryCodeList(ArrayList<String> countryCodeList) {
        this.countryCodeList = countryCodeList;
    }
}
