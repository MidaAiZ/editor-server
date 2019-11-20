package net.tabplus.api.components.quartz.job;

import net.tabplus.api.modules.pojo.LoginRecord;
import net.tabplus.api.modules.service.LoginRecordService;
import net.tabplus.api.modules.pojo.DownloadRecord;
import net.tabplus.api.modules.service.DownloadRecordService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

/**
 * @author lihaoyu
 * @date 2019/10/6 14:05
 */
@Slf4j
@Component
@PersistJobDataAfterExecution // 持久化JobDataMap里的数据，使下一个定时任务还能获取到这些值
@DisallowConcurrentExecution // 禁止并发多任务执行，所以永远只有一个任务在执行中
public class SiteViewHistoryJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private LoginRecordService loginRecordService;

    @Autowired
    private DownloadRecordService downloadRecordService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("开始执行登录记录存库操作");
        executeTask();
        log.info("登录记录存库操作结束");
    }

    public void executeTask() {
        long count = 0;
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(LoginRecordService.CACHE_KEY, ScanOptions.scanOptions().build());
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            try {
                LoginRecord record = (LoginRecord) entry.getValue();
                saveData(record);
                redisTemplate.opsForHash().delete(LoginRecordService.CACHE_KEY, LoginRecordService.getHashKey(record));
                count++;
            } catch (Exception e) {
                log.error("【登录记录存库操作报错】, 记录值：" + entry.getValue() + "，报错信息：" + e.getMessage());
            }
        }
        log.info("新增" + count + "条登录记录");
    }

    /**
     * 持久化数据
     *
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    void saveData(LoginRecord record) {
        loginRecordService.save(record);
        // 如果不存在这个登录记录的话则记作1个下载记录
        String did = record.getClientId().replaceAll("-", "");
        if (downloadRecordService.getById(did) == null) {
            DownloadRecord dRecod = new DownloadRecord();
            dRecod.setDid(did);
            dRecod.setIp(record.getIp());
            dRecod.setUa(record.getUa());
            dRecod.setCountryCode(record.getCountryCode());
            dRecod.setCreatedAt(record.getLoginTime());
            downloadRecordService.save(dRecod);
        }
    }
}

