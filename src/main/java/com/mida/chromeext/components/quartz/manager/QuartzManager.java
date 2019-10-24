package com.mida.chromeext.components.quartz.manager;

import com.mida.chromeext.components.quartz.job.BgPictureJob;
import com.mida.chromeext.components.quartz.job.SiteViewHistoryJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author lihaoyu
 * @date 2019/10/6 14:10
 */
@Configuration
public class QuartzManager {

    public static final String BG_PIC_JOB = "bg_pic_job";
    public static final String LOGIN_RECORD_SAVE_JOB = "login_record_save_job";
    public static final String DEFAULT_GROUP = "default_group";
    //默认每个星期凌晨一点执行
    // 每五秒 */5 * * * * ?     每天10点触发一次  0 0 10 * * ?
    //public static final String CRON_AT_EVERY_DAY_10H="0 0 1 ? * L";
    /**
     * 每10秒执行一次
     */
    public static final String CRON_AT_EVERY_10SECOND = "*/10 * * * * ?";

    /**
     * 每天10点触发一次
     **/
    public static final String CRON_AT_EVERY_DAY_10H = "0 0 1 * * ?";

    /**
     * 每半小时执行一次
     */
    public static final String CRON_AT_EVERY_HALF_HOUR = "0 0/30 * * *";

    /**
     * 每小时执行一次
     */
    public static final String CRON_AT_EVERY_HOUR = "0 0 0/1 * * ?";

    /**
     * 任务调度
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 开始执行定时任务
     */
    public void startJob() throws SchedulerException {
        startJobTask(scheduler);
        scheduler.start();
    }

    /**
     * 启动定时任务
     *
     * @param scheduler 调度器
     * @author lihaoyu
     * @date 2019/10/6 16:46
     */
    private void startJobTask(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail;
        CronTrigger cronTrigger;
        /**
         * 壁纸抓取
         */
        jobDetail = JobBuilder.newJob(BgPictureJob.class).withIdentity(BG_PIC_JOB, DEFAULT_GROUP).build();
        cronTrigger = TriggerBuilder.newTrigger().withIdentity(BG_PIC_JOB, DEFAULT_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_AT_EVERY_DAY_10H)).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

        /**
         * 用户登录记录保存
         */
        jobDetail = JobBuilder.newJob(SiteViewHistoryJob.class).withIdentity(LOGIN_RECORD_SAVE_JOB, DEFAULT_GROUP).build();
        cronTrigger = TriggerBuilder.newTrigger().withIdentity(LOGIN_RECORD_SAVE_JOB, DEFAULT_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_AT_EVERY_10SECOND)).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 获取Job信息
     *
     * @param name  名字
     * @param group 组
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改任务的执行时间
     *
     * @param name  名字
     * @param group 组
     * @param cron  cron表达式
     * @return boolean
     * @throws SchedulerException 异常
     */
    public boolean modifyJob(String name, String group, String cron) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cron)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException 异常
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name  名字
     * @param group 组
     * @throws SchedulerException 异常
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException 异常
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务BgPictureJob
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除某个任务
     *
     * @param name  名字
     * @param group 组
     * @throws SchedulerException 异常
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

}