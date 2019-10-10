package com.mida.chromeext.quartz.config;

import com.mida.chromeext.quartz.factory.MyJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author lihaoyu
 * @date 2019/10/6 21:00
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    @Qualifier("schedulerFactoryBean2")
    private SchedulerFactoryBean schedulerFactoryBean;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean2() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 初始注入scheduler
     */
    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean.getScheduler();
    }

}
