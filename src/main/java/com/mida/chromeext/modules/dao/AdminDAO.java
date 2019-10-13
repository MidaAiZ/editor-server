package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.AdminMapper;
import com.mida.chromeext.modules.pojo.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 19:59
 */
@Repository
public interface AdminDAO extends AdminMapper {
   Admin selectAdminByNumber(String number);
}
