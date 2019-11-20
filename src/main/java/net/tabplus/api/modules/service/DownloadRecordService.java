package net.tabplus.api.modules.service;

import net.tabplus.api.modules.pojo.DownloadRecord;
import net.tabplus.api.modules.pojo.DownloadRecordExample;
import net.tabplus.api.modules.vo.statistic.StatisticCountVo;
import net.tabplus.api.utils.NumConst;
import net.tabplus.api.modules.dao.DownloadRecordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DownloadRecordService {
    @Autowired
    private DownloadRecordDAO downloadRecordDAO;

    /**
     * 保存一条下载记录，所有的字段应该事先设置好
     *
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
     *
     * @param id
     * @return
     */
    public DownloadRecord getById(String id) {
        return downloadRecordDAO.selectByPrimaryKey(id);
    }

    /**
     * 统计每日下载量
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Long> listDailyDloadCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = downloadRecordDAO.listDailyDloadsCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        for (StatisticCountVo vo : voList) {
            resMap.put(vo.getName(), vo.getCount());
        }
        return resMap;
    }


    /**
     * 统计每月下载量
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public Map<String, Long> listMonthlyDloadCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = downloadRecordDAO.listMonthlyDloadsCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        voList.forEach(vo -> resMap.put(vo.getName(), vo.getCount()));
        return resMap;
    }

    /**
     * 统计下载量
     *
     * @return 总数
     */
    public Long getAllDloadCount() {
        return downloadRecordDAO.countByExample(new DownloadRecordExample());
    }
}
