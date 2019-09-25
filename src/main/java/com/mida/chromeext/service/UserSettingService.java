package com.mida.chromeext.service;

import com.mida.chromeext.dao.UserSettingDAO;
import com.mida.chromeext.pojo.UserSetting;
import com.mida.chromeext.pojo.UserSettingExample;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 16:05
 */
@Service
public class UserSettingService {
    @Autowired
    UserSettingDAO userSettingDAO;

    /**
     * 根据用户Id查询其用户设置
     *
     * @param userId
     * @return UserSetting Po
     * @author lihaoyu
     * @date 2019/9/22 16:12
     */
    public UserSetting getUserSettingByUserId(Integer userId) {
        UserSetting userSetting = userSettingDAO.selectOneByUserId(userId);
        return userSetting;
    }

    /**
     * 用户添加配置(如果配置已存在则更新)
     *
     * @param setting Po
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 16:17
     */
    public boolean addUserSetting(Integer userId, UserSetting setting) {
        // 如果已存在记录，则执行更新方法
        if (setting == null || getUserSettingByUserId(userId) != null) {
            return updateUserSetting(userId, setting);
        }
        // 设置userId
        setting.setUid(userId);
        int affectedRows = userSettingDAO.insertSelective(setting);
        if (affectedRows != NumConst.NUM1) {
            return false;
        }
        return true;
    }

    /**
     * 用户修改自定义配置，若不存在新建配置
     *
     * @param setting Po
     * @return boolean 是否修改成功
     * @author lihaoyu
     * @date 2019/9/22 16:30
     */
    public boolean updateUserSetting(Integer userId, UserSetting setting) {
        // 如果记录不存在，则插入用户设置
        UserSetting existSetting = getUserSettingByUserId(userId);
        if (existSetting == null) {
            return addUserSetting(userId, setting);
        } else {
            setting.setSid(existSetting.getSid());
            setting.setUid(null);
        }
        int affectedRows = userSettingDAO.updateByPrimaryKeySelective(setting);
        if (affectedRows != NumConst.NUM1) {
            return false;
        }
        return true;
    }


}
