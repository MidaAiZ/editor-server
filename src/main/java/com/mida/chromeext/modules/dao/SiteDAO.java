package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.SiteMapper;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.modules.vo.SiteRelationVo;
import com.mida.chromeext.modules.vo.statistic.CategorySitesCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:49
 */
@Repository
public interface SiteDAO extends SiteMapper {

    Map<String, String> listAllSiteURL();

    /**
     * 网站被引用数自增1
     *
     * @param sid
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 13:31
     */
    int increaseUsedCount(Integer sid);

    /**
     * 网站被引用数自减1
     *
     * @param sid
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 13:31
     */
    int decreaseUsedCount(Integer sid);

    /**
     * 批量 网站被引用数自增1
     *
     * @param sidList
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 14:04
     */
    int batchIncreaseUsedCount(List<Integer> sidList);


    /**
     * 批量 网站被引用数自减1
     *
     * @param sidList
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 14:04
     */
    int batchDecreaseUsedCount(List<Integer> sidList);

    /**
     * 分页分类分国家按序查找 site
     *
     * @param queryVo 查询条件
     * @return List<Site>
     * @author lihaoyu
     * @date 2019/9/28 21:36
     */
    List<Site> queryList(@Param("queryVo") SiteListQueryVo queryVo);

    /**
     * 查询网站列表，并获取相关关联
     *
     * @param queryVo
     * @return
     */
    List<SiteRelationVo> queryListWithRelations(@Param("queryVo") SiteListQueryVo queryVo);

    /**
     * 统计所有种类下的网站个数
     *
     * @return
     * @author lihaoyu
     * @date 2019/10/19 21:17
     */
    List<CategorySitesCount> listSitesCountByCategory();

    /**
     * 获取实时热门站点
     * @return
     */
    List<Map> listHotSites();
}
