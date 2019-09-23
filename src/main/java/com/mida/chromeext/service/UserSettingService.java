package com.mida.chromeext.service;

import com.mida.chromeext.dao.UserSettingDAO;
import com.mida.chromeext.pojo.UserSetting;
import com.mida.chromeext.pojo.UserSettingExample;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 16:05
 */
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
        UserSettingExample example = new UserSettingExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserSetting> userSettings = userSettingDAO.selectByExample(example);
        return userSettings.isEmpty() ? null : userSettings.get(NumConst.NUM0);
    }

    /**
     * 用户添加配置(校验是否已存在)
     *
     * @param setting Po
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 16:17
     */
    public boolean addUserSetting(UserSetting setting) {
        // 如果已存在记录，返回false
        if (setting == null || getUserSettingByUserId(setting.getUid()) != null) {
            return false;
        }
        int affectedRows = userSettingDAO.insertSelective(setting);
        if (affectedRows != NumConst.NUM1) {
            return false;
        }
        return true;
    }

    /**
     * 用户修改自定义配置
     *
     * @param setting Po
     * @return boolean 是否修改成功
     * @author lihaoyu
     * @date 2019/9/22 16:30
     */
    public boolean updateUserSetting(UserSetting setting) {
        if (setting == null || setting.getSid() == null ||
                setting.getSid() == null || setting.getSettings() == null) {
            return false;
        }
        // 如果记录不存在，返回false
        if (getUserSettingByUserId(setting.getUid()) == null) {
            return false;
        }
        int affectedRows = userSettingDAO.updateByPrimaryKey(setting);
        if (affectedRows != NumConst.NUM1) {
            return false;
        }
        return true;
    }


}
