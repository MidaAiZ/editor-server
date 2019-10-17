package com.mida.chromeext.modules.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 管理员通过邮箱，Uid，国家码，注册时间，进行用户查询
 * @author lihaoyu
 * @date 2019/10/17 14:58
 */
public class MngUserListQueryVo extends ListQueryVo{


    private List<String> emailList;

    private List<String> countryCodeList;

    private List<Integer> userIdList;

    /***
     * 注册的开始和结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public List<String> getCountryCodeList() {
        return countryCodeList;
    }

    public void setCountryCodeList(List<String> countryCodeList) {
        this.countryCodeList = countryCodeList;
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }
}
