package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.UserSettingMapper;
import com.mida.chromeext.pojo.UserSetting;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface UserSettingDAO extends UserSettingMapper {
    UserSetting selectOneByUserId(int userId);
}
