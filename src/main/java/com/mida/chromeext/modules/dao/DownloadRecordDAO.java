package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.DownloadRecordMapper;
import com.mida.chromeext.modules.pojo.BgPicture;
import com.mida.chromeext.modules.pojo.DownloadRecord;

import java.util.List;

public interface DownloadRecordDAO extends DownloadRecordMapper {
    int insertWithId(DownloadRecord record);
}
