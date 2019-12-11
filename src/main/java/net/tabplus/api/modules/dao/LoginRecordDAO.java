package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.LoginRecordMapper;
import net.tabplus.api.modules.pojo.LoginRecord;

public interface LoginRecordDAO extends LoginRecordMapper {
    int insertWithId(LoginRecord record);
}