package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.BgPictureMapper;
import com.mida.chromeext.pojo.BgPicture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:47
 */
@Repository
public interface BgPictureDAO extends BgPictureMapper {

    /**
     * 批量插入背景图片
     *
     * @param list BgPicture集合
     * @return affectRow
     * @author lihaoyu
     * @date 2019/10/7 15:37
     */
    int batchInsertBgPicture(List<BgPicture> list);

    /**
     * 随机获取一定数量的图片
     *
     * @param number 数量
     * @return BgPicture List
     * @author lihaoyu
     * @date 2019/10/7 15:43
     */
    List<BgPicture> listRandomBgPicture(int number);


}
