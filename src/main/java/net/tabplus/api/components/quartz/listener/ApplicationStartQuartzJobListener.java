package net.tabplus.api.components.quartz.listener;

import net.tabplus.api.components.quartz.manager.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author lihaoyu
 * @date 2019/10/6 14:16
 */
@Slf4j
@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            quartzManager.startJob();
            log.info("任务已经启动......");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}