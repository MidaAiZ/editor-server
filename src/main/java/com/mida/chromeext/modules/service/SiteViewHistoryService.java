package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.interceptor.UserAuthorizationInterceptor;
import com.mida.chromeext.modules.dao.SiteViewHistoryDAO;
import com.mida.chromeext.modules.pojo.SiteViewHistory;
import com.mida.chromeext.modules.pojo.SiteViewHistoryExample;
import com.mida.chromeext.modules.vo.SiteViewHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SiteViewHistoryService {
    @Autowired
    private SiteViewHistoryDAO siteViewHistoryDAO;

    // TODO 后期加入缓存，批量定时插入，减轻数据库压力
    public SiteViewHistory create(SiteViewHistory history, HttpServletRequest request) {
        history.setLastViewTime(new Date());
        history.setCreatedAt(new Date());
        history.setTimes(1);
        // 设置请求IP
        history.setIp(request.getRemoteAddr());
        // 设置请求浏览器信息
        history.setBrowserUa(request.getHeader("user-agent"));
        // 设置用户ID
        Object uid = request.getAttribute(UserAuthorizationInterceptor.CURRENT_USER);
        if (uid != null) {
            history.setUserId(Integer.valueOf((String) uid));
        }
        // 设置浏览记录主键
        history.setHid(UUID.randomUUID().toString().replace("-", ""));
        siteViewHistoryDAO.insertWithUUID(history);
        return history;
    }

    public List<SiteViewHistory> getList(SiteViewHistoryVo queryVo) {
        SiteViewHistoryExample example = new SiteViewHistoryExample();
        SiteViewHistoryExample.Criteria criteria = example.createCriteria();

        if (queryVo.getUserIdList() != null) {
            criteria.andUserIdIn(queryVo.getUserIdList());
        }
        if (queryVo.getIpList() != null) {
            criteria.andIpIn(queryVo.getIpList());
        }
        if (queryVo.getSiteUrlList() != null) {
            criteria.andIpIn(queryVo.getSiteUrlList());
        }
        if (queryVo.getSiteIdList() != null) {
            criteria.andIpIn(queryVo.getSiteIdList());
        }
        if (queryVo.getSiteTitleList() != null) {
            criteria.andIpIn(queryVo.getSiteTitleList());
        }
        if (!StringUtils.isEmpty(queryVo.getSiteTitle())) {
            criteria.andSiteTitleLike(queryVo.getSiteTitle());
        }
        if (!StringUtils.isEmpty(queryVo.getSiteUrl())) {
            criteria.andSiteUrlLike(queryVo.getSiteUrl());
        }
        if (queryVo.getCreatedBefore() != null) {
            criteria.andCreatedAtLessThanOrEqualTo(queryVo.getCreatedBefore());
        }
        if (queryVo.getCreatedAfter() != null) {
            criteria.andCreatedAtGreaterThanOrEqualTo(queryVo.getCreatedAfter());
        }
        if (queryVo.getLastViewTimeBefore() != null) {
            criteria.andLastViewTimeLessThanOrEqualTo(queryVo.getLastViewTimeBefore());
        }
        if (queryVo.getLastViewTimeAfter() != null) {
            criteria.andLastViewTimeGreaterThanOrEqualTo(queryVo.getLastViewTimeAfter());
        }

        PageHelper.startPage(queryVo);
        List<SiteViewHistory> histories = siteViewHistoryDAO.selectByExample(example);
        return histories;
    }
}
