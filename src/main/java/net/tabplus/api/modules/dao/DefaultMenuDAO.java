package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.DefaultMenuMapper;
import net.tabplus.api.modules.pojo.DefaultMenu;
import net.tabplus.api.modules.vo.DefaultMenuRelVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultMenuDAO extends DefaultMenuMapper {
    DefaultMenu selectByCountryCode(String code);

    List<DefaultMenuRelVo> listAllMenus();
}
