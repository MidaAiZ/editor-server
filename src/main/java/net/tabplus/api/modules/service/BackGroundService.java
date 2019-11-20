package net.tabplus.api.modules.service;

import net.tabplus.api.modules.dao.BgPictureDAO;
import net.tabplus.api.modules.pojo.BgPicture;
import net.tabplus.api.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lihaoyu
 * @date 2019/9/22 16:33
 */
@Service
public class BackGroundService {
    @Autowired
    BgPictureDAO bgPictureDAO;

    /**
     * 添加背景图片
     *
     * @param bgPicture 必须有 src和title值
     * @param adminId   添加者（管理员）id
     * @return BgPicture Po
     * @author lihaoyu
     * @date 2019/9/22 20:11
     */
    public BgPicture addBgPicture(BgPicture bgPicture, Integer adminId) {
        if (bgPicture == null || bgPicture.getSrc() == null || bgPicture.getTitle() == null) {
            return null;
        }
        bgPicture.setCreatedBy(adminId);
        bgPicture.setCreatedAt(new Date());
        int affectRows = bgPictureDAO.insertSelective(bgPicture);
        if (affectRows != NumConst.NUM1) {
            return null;
        }
        return bgPicture;
    }


}
