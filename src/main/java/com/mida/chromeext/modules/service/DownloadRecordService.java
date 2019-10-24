package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.DownloadRecordDAO;
import com.mida.chromeext.modules.pojo.DownloadRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DownloadRecordService {
    @Autowired
    private DownloadRecordDAO downloadRecordDAO;

    /**
     * 保存一条下载记录，所有的字段应该事先设置好
     * @param record
     * @return
     */
    public boolean save(DownloadRecord record) {
        if (record.getCreatedAt() == null) {
            Date now = new Date();
            record.setCreatedAt(now);
        }
        return downloadRecordDAO.insertWithId(record) > 0;
    }

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public DownloadRecord getById(String id) {
        return downloadRecordDAO.selectByPrimaryKey(id);
    }
}
