package com.mida.chromeext.modules.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.BgPictureDAO;
import com.mida.chromeext.modules.pojo.BgPicture;
import com.mida.chromeext.modules.pojo.BgPictureExample;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.modules.vo.ListResultVo;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 15:29
 */
@Service
public class BgPictureService {

    @Autowired
    BgPictureDAO bgPictureDAO;

    /**
     * 随机获取一条数据
     *
     * @return
     */
    public BgPicture getRandomBgPicture() {
        List<BgPicture> list = bgPictureDAO.listRandomBgPicture(NumConst.NUM1);
        return list.get(NumConst.NUM0);
    }

    /**
     * 通过主键获取一条记录
     */
    public BgPicture getById(Integer pid) {
        return bgPictureDAO.selectByPrimaryKey(pid);
    }

    /**
     * 获取多条数据
     *
     * @param queryVo
     * @return
     */
    public ListResultVo<BgPicture> getList(ListQueryVo queryVo) {
        BgPictureExample ex = new BgPictureExample();
        Page page = PageHelper.startPage(queryVo);
        bgPictureDAO.selectByExample(ex);
        return new ListResultVo(page);
    }

    /**
     * 插入一条数据
     *
     * @param picture
     * @return
     */
    public Boolean create(Integer adminId, BgPicture picture) {
        Date date = new Date();
        picture.setCreatedAt(date);
        picture.setCreatedBy(adminId);
        return bgPictureDAO.insertSelective(picture) > 0;
    }

    /**
     * 通过主键更新一条数据
     *
     * @param picture
     * @return
     */
    public Boolean updateById(BgPicture picture) {
        return bgPictureDAO.updateByPrimaryKeySelective(picture) > 0;
    }

    /**
     * 通过主键删除一条记录
     *
     * @param pid
     * @return
     */
    public Boolean deleteById(Integer pid) {
        return bgPictureDAO.deleteByPrimaryKey(pid) > 0;
    }
}
