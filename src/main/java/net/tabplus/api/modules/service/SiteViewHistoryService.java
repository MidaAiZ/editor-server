package net.tabplus.api.modules.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.tabplus.api.annotation.LoginRequired;
import net.tabplus.api.modules.vo.statistic.StatisticCountVo;
import net.tabplus.api.interceptor.UserAuthorizationInterceptor;
import net.tabplus.api.modules.dao.SiteViewHistoryDAO;
import net.tabplus.api.modules.pojo.SiteViewHistory;
import net.tabplus.api.modules.pojo.SiteViewHistoryExample;
import net.tabplus.api.modules.vo.ListResultVo;
import net.tabplus.api.modules.vo.SiteViewHistoryVo;
import net.tabplus.api.utils.Constant;
import net.tabplus.api.utils.LocaleHelper;
import net.tabplus.api.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SiteViewHistoryService {
    @Autowired
    private SiteViewHistoryDAO siteViewHistoryDAO;

    // TODO 后期加入缓存，批量定时插入，减轻数据库压力

    /**
     * 创建浏览记录
     *
     * @param history
     * @param request
     * @return
     */
    public SiteViewHistory create(SiteViewHistory history, HttpServletRequest request) {
        history.setCreatedAt(new Date());
        // 设置请求IP
        history.setIp(request.getRemoteAddr());
        // 设置请求浏览器信息
        history.setBrowserUa(request.getHeader(Constant.USER_AGENT_HEADER));
        // 设置请求插件平台信息
        history.setPluginPlatform(request.getHeader(Constant.PLUGIN_PLATFORM_HEADER));
        // 设置countryCode
        history.setCountryCode(LocaleHelper.getContextCountryCode(request));
        // 设置用户ID
        Object uid = request.getAttribute(UserAuthorizationInterceptor.CURRENT_USER);
        if (uid != null) {
            history.setUserId(Integer.valueOf((String) uid));
        }
        // 设置浏览记录主键
        history.setHid(UUID.randomUUID().toString().replace("-", ""));
        siteViewHistoryDAO.insertSelectiveWithUUID(history);
        return history;
    }

    /**
     * 根据条件查询浏览记录列表
     *
     * @param queryVo
     * @return
     */
    public ListResultVo<SiteViewHistory> getList(SiteViewHistoryVo queryVo) {
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

        example.setOrderByClause("created_at desc");
        Page page = PageHelper.startPage(queryVo);
        siteViewHistoryDAO.selectByExample(example);
        return new ListResultVo(page);
    }


    /**
     * 获取浏览记录日数据
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Long> listDailyViewCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = siteViewHistoryDAO.listDailyViewCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        for (StatisticCountVo vo : voList) {
            resMap.put(vo.getName(), vo.getCount());
        }
        return resMap;
    }


    /**
     * 获取浏览数据月数据
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Long> listMonthlyViewCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = siteViewHistoryDAO.listMonthlyViewCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        for (StatisticCountVo vo : voList) {
            resMap.put(vo.getName(), vo.getCount());
        }
        return resMap;
    }

    /**
     * 获取浏览量总数
     */
    public Long getAllViewsCount() {
        return siteViewHistoryDAO.countByExample(new SiteViewHistoryExample());
    }
}
