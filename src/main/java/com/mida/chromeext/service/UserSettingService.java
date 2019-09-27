package com.mida.chromeext.service;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.dao.UserSettingDAO;
import com.mida.chromeext.dto.DefaultUserSettingDto;
import com.mida.chromeext.pojo.UserSetting;
import com.mida.chromeext.pojo.UserSettingExample;
import com.mida.chromeext.utils.MergeObject;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    private ApplicationContext applicationContext;

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
     * @param dd Po
     * @return DefaultUserSettingDto 插入的配置
     * @author lihaoyu
     * @date 2019/9/22 16:17
     */
    public DefaultUserSettingDto addUserSetting(Integer userId, DefaultUserSettingDto dd) {
        // 如果已存在记录，则执行更新方法
        if (dd == null || getUserSettingByUserId(userId) != null) {
            return updateUserSetting(userId, dd);
        }
        // 设置userId
        UserSetting setting = new UserSetting();
        try {
            dd =  MergeObject.merge(applicationContext.getBean(DefaultUserSettingDto.class), dd);
            setting.setSettings(JSONObject.toJSONString(dd));
        } catch (Exception e) {
            System.out.println(e);
        }
        setting.setSettings(JSONObject.toJSONString(dd));
        setting.setUid(userId);
        int affectedRows = userSettingDAO.insertSelective(setting);
        if (affectedRows != NumConst.NUM1) {
            return null;
        }
        return dd;
    }

    /**
     * 用户修改自定义配置,将会合并默认配置，若不存在新建配置
     *
     * @param setting Po
     * @return DefaultUserSettingDto 插入的配置
     * @author lihaoyu
     * @date 2019/9/22 16:30
     */
    public DefaultUserSettingDto updateUserSetting(Integer userId, DefaultUserSettingDto setting) {
        // 如果记录不存在，则插入用户设置
        UserSetting existSetting = getUserSettingByUserId(userId);
        DefaultUserSettingDto dd = null;
        if (existSetting == null) {
            return addUserSetting(userId, setting);
        } else {
            try {
                dd = MergeObject.merge(JSONObject.parseObject(existSetting.getSettings(), DefaultUserSettingDto.class), setting);
                existSetting.setSettings(JSONObject.toJSONString(dd));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        int affectedRows = userSettingDAO.updateByPrimaryKeySelective(existSetting);
        if (affectedRows != NumConst.NUM1) {
            return null;
        }
        return dd;
    }

}
