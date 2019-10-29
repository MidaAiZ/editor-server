package com.mida.chromeext.modules.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mida.chromeext.exception.MyException;
import com.mida.chromeext.modules.dao.SiteDAO;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.pojo.SiteExample;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.vo.ListResultVo;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.modules.vo.SiteRelationVo;
import com.mida.chromeext.modules.vo.statistic.CategorySitesCount;
import com.mida.chromeext.utils.Constant;
import com.mida.chromeext.utils.MergeObject;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lihaoyu
 * @date 2019/9/17 12:45
 */
@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    @Autowired
    private CountriesSitesService countriesSitesService;

    @Autowired
    private SiteCategoryService siteCategoryService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    /**
     * 根据 sid 查询网站Po
     *
     * @param id 网站id
     * @return 网站Po 或者 null
     * @author lihaoyu
     * @date 2019/9/17 13:06
     */
    public Site getSiteById(Integer id) {
        Site site = siteDAO.selectByPrimaryKey(id);
        return site;
    }

    /**
     * 根据sid查询网站，包含关联信息
     */
    public SiteRelationVo getSiteByIdWithRelations(Integer id) {
        Site site = getSiteById(id);
        if (site != null) {
            SiteRelationVo siteRel =  JSONObject.parseObject(JSONObject.toJSON(site).toString(), SiteRelationVo.class);
            if (siteRel.getCreatorType().equals(Constant.CREATED_BY_USER)) {
                siteRel.setCreatedUser(userService.getUserById(site.getCreatorId()));
            } else if (siteRel.getCreatorType().equals(Constant.CREATED_BY_ADMIN)) {
                siteRel.setCreatedAdmin(adminService.getAdminById(site.getCreatorId()));
            }
            siteRel.setCategory(siteCategoryService.getCateById(site.getCateId()));
            siteRel.setCountryList(countryService.listCountriesBySiteId(site.getSid()));
            return siteRel;
        }

        return null;
    }

    /**
     * 管理员添加网站列表   同时添加 site 和 country 关联
     *
     * @param SiteAddVos
     * @param adminId
     * @return List<Site>
     * @author lihaoyu
     * @date 2019/9/28 21:30
     */
    @Transactional
    public List<Site> addSitesByAdmin(List<SiteAddVo> SiteAddVos, Integer adminId) {
        List<Site> addSiteList = new ArrayList<>();
        Date date = new Date();
        for (SiteAddVo siteAddVo : SiteAddVos) {
            Site site = Site.builder().creatorId(adminId).createdAt(date).updatedAt(date)
                    .icon(siteAddVo.getIcon()).title(siteAddVo.getTitle()).cateId(siteAddVo.getCateId())
                    .weight(siteAddVo.getWeight()).creatorType(Constant.CREATED_BY_ADMIN).state(Constant.SITE_STATE_OK)
                    .url(siteAddVo.getUrl()).build();
            siteDAO.insertSelective(site);
            addSiteList.add(site);

            SiteCategory category = siteCategoryService.getCateById(site.getCateId());
            if (category == null) {
                throw new MyException("No such category with ID = " + site.getCateId().toString());
            }

            // 添加国家网站关联表
            countriesSitesService.AddRelations(site.getSid(), siteAddVo.getCountryCodes());
        }
        return addSiteList;
    }

    /**
     * 用户添加网站
     * 添加的网站需要由管理员审核
     * 审核通过后才可以被搜索到
     * @param siteAddVo
     * @param user
     * @return
     */
    @Transactional
    public Site addOneSiteByUser(SiteAddVo siteAddVo, User user) {
        Date date = new Date();
        Site newSite = Site.builder().createdAt(date).updatedAt(date).creatorType(Constant.CREATED_BY_USER).creatorId(user.getUid())
                        .cateId(siteAddVo.getCateId()).title(siteAddVo.getTitle()).url(siteAddVo.getUrl()).icon(siteAddVo.getIcon())
                        .weight(50F).state(Constant.SITE_STATE_IN_REVIEWING).build();
        siteDAO.insertSelective(newSite);
        // 设置网站所适用的国家
        String countryCode = user.getCountryCode();
        if (StringUtils.isEmpty(countryCode)) { countryCode = Constant.THE_WORLD; }
        countriesSitesService.AddRelations(newSite.getSid(), Lists.newArrayList(countryCode));
        return newSite;
    }

    /**
     * 更新一条网站数据
     *
     * @param site
     * @return
     */
    public Boolean update(Site site) {
        site.setUpdatedAt(new Date());
        return siteDAO.updateByPrimaryKeySelective(site) > 0;
    }


    /**
     * 通过主键删除网站，同时移除网站-国家
     * @param sid
     * @return
     */
    @Transactional
    public boolean deleteById(Integer sid) {
        countriesSitesService.removeRelationsBySiteId(sid);
        return siteDAO.deleteByPrimaryKey(sid) > 0;
    }

    /**
     * 删除多条网站记录，同时删除关联
     * @param sids
     * @return
     */
    public int deleteSitesByIds(List<Integer> sids) {
        int count = 0;
        for (int id : sids) {
            if (deleteById(id)) { count++; }
        }
        return count;
    }

    /**
     * 当用户添加网站时，此网站的UsedCount要自增1
     *
     * @param sid 要增加的网站id
     * @return boolean 0表示sid不存在或自增失败，1表示自增成功
     * @author lihaoyu
     * @date 2019/9/19 14:58
     */
    public boolean increaseUsedCount(Integer sid) {
        int affectedRows = siteDAO.increaseUsedCount(sid);
        return affectedRows == NumConst.NUM1;
    }

    /**
     * 当用户删除网站时，此网站的UsedCount要自减1
     *
     * @param sid 要删除的网站id
     * @return boolean 0表示sid不存在或自减失败，1表示自减成功
     * @author lihaoyu
     * @date 2019/9/19 14:58
     */
    public boolean decreaseUsedCount(Integer sid) {
        int affectedRows = siteDAO.decreaseUsedCount(sid);
        return affectedRows == NumConst.NUM1;
    }

    /**
     * 批量网站的引用计数增1
     *
     * @param sidList
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 14:14
     */
    public boolean batchIncreaseUsedCount(List<Integer> sidList) {
        int affectedRows = siteDAO.batchIncreaseUsedCount(sidList);
        return affectedRows == sidList.size();
    }

    /**
     * 批量网站的引用计数减1
     *
     * @param sidList
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 14:14
     */
    public boolean batchDecreaseUsedCount(List<Integer> sidList) {
        int affectedRows = siteDAO.batchDecreaseUsedCount(sidList);
        return affectedRows == sidList.size();
    }

    /**
     * 判断网站是否都在数据库中
     *
     * @param sidList
     * @return 是否都存在
     * @author lihaoyu
     * @date 2019/9/22 14:22
     */
    public boolean isExist(List<Integer> sidList) {
        if (sidList == null || sidList.size() == NumConst.NUM0) {
            return false;
        }
        SiteExample example = new SiteExample();
        example.createCriteria().andSidIn(sidList);
        long count = siteDAO.countByExample(example);
        return count == sidList.size();
    }

    /**
     * 前台查询网站列表
     *
     * @param queryVo
     * @return
     */
    public List<Site> queryList(SiteListQueryVo queryVo) {
        PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize(), false);
        List<Site> sites = siteDAO.queryList(queryVo);
        return sites;
    }

    /**
     * 后台查询网站列表，获取关联对象
     *
     * @param queryVo
     * @return
     */
    public ListResultVo<SiteRelationVo> queryListWithRelations(SiteListQueryVo queryVo) {
        Page<SiteRelationVo> page = PageHelper.startPage(queryVo);
        siteDAO.queryListWithRelations(queryVo);
        return new ListResultVo(page);
    }

    /**
     * 统计所有网站分类下的网站个数
     *
     * @param
     * @author lihaoyu
     * @date 2019/10/19 21:17
     */
    public List<CategorySitesCount> listSitesCountByCategory() {
        return siteDAO.listSitesCountByCategory();
    }

    /**
     * 统计网站总数
     *
     * @return 总数
     * @author lihaoyu
     * @date 2019/10/19 21:36
     */
    public Long countAll() {
        long count = siteDAO.countByExample(new SiteExample());
        return count;
    }

    /**
     * 获取系统创建的网站数
     * @return
     */
    public Long countSysSites() {
        SiteExample example = new SiteExample();
        example.createCriteria().andCreatorTypeEqualTo(Constant.CREATED_BY_ADMIN);
        return siteDAO.countByExample(example);
    }


    /**
     * 获取用户创建的网站数
     * @return
     */
    public Long countUserSites() {
        SiteExample example = new SiteExample();
        example.createCriteria().andCreatorTypeEqualTo(Constant.CREATED_BY_USER);
        return siteDAO.countByExample(example);
    }

    /**
     * 获取当日热门站点
     * @return
     */
    public List<Map> hotSites() {
        return siteDAO.listHotSites();
    }
}
