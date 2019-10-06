package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.DefaultMenuMapper;
import com.mida.chromeext.pojo.DefaultMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultMenuDAO extends DefaultMenuMapper {
    DefaultMenu selectByCountryCode(String code);

    DefaultMenu selectDefaultMenu();
}
