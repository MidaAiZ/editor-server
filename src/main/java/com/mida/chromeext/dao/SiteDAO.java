package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.SiteMapper;
import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.vo.SiteListQueryVo;
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
    List<Site> listSitesByPage(@Param("queryVo") SiteListQueryVo queryVo);

    /**
     * 列出所有网站的名字
     *
     * @return 所有名字
     * @author lihaoyu
     * @date 2019/9/28 21:37
     */
    Set<String> listAllTitle();
}
