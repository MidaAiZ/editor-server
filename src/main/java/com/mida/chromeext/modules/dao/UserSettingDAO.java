package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.UserSettingMapper;
import com.mida.chromeext.modules.pojo.UserSetting;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface UserSettingDAO extends UserSettingMapper {
    UserSetting selectOneByUserId(int userId);
}
