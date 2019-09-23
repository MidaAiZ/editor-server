package com.mida.chromeext.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mida.chromeext.dao.BgPictureDAO;
import com.mida.chromeext.pojo.BgPicture;
import com.mida.chromeext.utils.NumConst;

/**
 * @author lihaoyu
 * @date 2019/9/22 16:33
 */
public class BackGroundService {
    @Autowired
    BgPictureDAO bgPictureDAO;

    private String pwd;

    /**
     * 添加背景图片
     *
     * @param bgPicture 必须有 src和title值
     * @param adminId 添加者（管理员）id
     * @return BgPicture Po
     * @author lihaoyu
     * @date 2019/9/22 20:11
     */
    public BgPicture addBgPicture(BgPicture bgPicture, Integer adminId){
        if(bgPicture == null || bgPicture.getSrc() == null || bgPicture.getTitle() == null){
            return null;
        }
        bgPicture.setCreatedBy(adminId);
        bgPicture.setCreatedAt(new Date());
        int affectRows = bgPictureDAO.insertSelective(bgPicture);
        if(affectRows != NumConst.NUM1){
            return null;
        }
        return bgPicture;
    }


}
