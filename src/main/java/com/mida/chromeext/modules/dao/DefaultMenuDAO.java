package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.DefaultMenuMapper;
import com.mida.chromeext.modules.pojo.DefaultMenu;
import com.mida.chromeext.modules.vo.DefaultMenuRelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultMenuDAO extends DefaultMenuMapper {
    DefaultMenu selectByCountryCode(String code);

    List<DefaultMenuRelVo> listAllMenus();
}
