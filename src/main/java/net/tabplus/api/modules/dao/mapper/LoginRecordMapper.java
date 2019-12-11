package net.tabplus.api.modules.dao.mapper;

import java.util.List;
import net.tabplus.api.modules.pojo.LoginRecord;
import net.tabplus.api.modules.pojo.LoginRecordExample;
import org.apache.ibatis.annotations.Param;

public interface LoginRecordMapper {
    long countByExample(LoginRecordExample example);

    int deleteByExample(LoginRecordExample example);

    int deleteByPrimaryKey(String rid);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    List<LoginRecord> selectByExample(LoginRecordExample example);

    LoginRecord selectByPrimaryKey(String rid);

    int updateByExampleSelective(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByExample(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}