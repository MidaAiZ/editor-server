package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.DefaultMenuMapper;
import com.mida.chromeext.modules.pojo.DefaultMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultMenuDAO extends DefaultMenuMapper {
    DefaultMenu selectByCountryCode(String code);

    DefaultMenu selectDefaultMenu();
}
