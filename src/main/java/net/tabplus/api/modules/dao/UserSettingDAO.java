package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.UserSettingMapper;
import net.tabplus.api.modules.pojo.UserSetting;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface UserSettingDAO extends UserSettingMapper {
    UserSetting selectOneByUserId(int userId);
}
