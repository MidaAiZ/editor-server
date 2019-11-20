package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.DownloadRecord;
import net.tabplus.api.modules.pojo.DownloadRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DownloadRecordMapper {
    long countByExample(DownloadRecordExample example);

    int deleteByExample(DownloadRecordExample example);

    int deleteByPrimaryKey(String did);

    int insert(DownloadRecord record);

    int insertSelective(DownloadRecord record);

    List<DownloadRecord> selectByExample(DownloadRecordExample example);

    DownloadRecord selectByPrimaryKey(String did);

    int updateByExampleSelective(@Param("record") DownloadRecord record, @Param("example") DownloadRecordExample example);

    int updateByExample(@Param("record") DownloadRecord record, @Param("example") DownloadRecordExample example);

    int updateByPrimaryKeySelective(DownloadRecord record);

    int updateByPrimaryKey(DownloadRecord record);
}