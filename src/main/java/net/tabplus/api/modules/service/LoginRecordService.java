package net.tabplus.api.modules.service;

import net.tabplus.api.modules.dao.LoginRecordDAO;
import net.tabplus.api.modules.pojo.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lihaoyu
 * @date 2019/10/17 16:55
 */
@Service
public class LoginRecordService {
    public static final String CACHE_KEY = "login-records";

    @Autowired
    LoginRecordDAO loginRecordDAO;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取缓存key值
     *
     * @param record
     * @return
     */
    public static String getHashKey(LoginRecord record) {
        return record.getClientId();
    }

    public void save(LoginRecord record) {
        loginRecordDAO.insertSelective(record);
    }

    /**
     * 把记录添加至缓存中，由定时器批量插入
     *
     * @param record
     * @return
     */
    public void addRecordCache(LoginRecord record) {
        Date now = new Date();
        record.setLoginTime(now);
        redisTemplate.opsForHash().put(LoginRecordService.CACHE_KEY, getHashKey(record), record);
    }
}
