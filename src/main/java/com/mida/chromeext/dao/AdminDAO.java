package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.AdminMapper;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 19:59
 */
@Repository
public interface AdminDAO extends AdminMapper {
   Admin selectAdminByNumber(String number);
}
