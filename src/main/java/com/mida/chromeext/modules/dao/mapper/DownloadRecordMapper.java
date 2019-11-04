package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.DownloadRecord;
import com.mida.chromeext.modules.pojo.DownloadRecordExample;
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