package com.mida.chromeext.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mida.chromeext.mapper.SiteMapper;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:49
 */
public interface SiteDAO extends SiteMapper {

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

}
