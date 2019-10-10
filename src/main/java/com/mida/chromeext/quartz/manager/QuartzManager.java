package com.mida.chromeext.quartz.manager;

import com.mida.chromeext.quartz.job.BgPictureJob;
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

    public static final String JOB1 = "job1";
    public static final String GROUP1 = "group1";
    //默认每个星期凌晨一点执行
    // 每五秒 */5 * * * * ?     每天10点触发一次  0 0 10 * * ?
    //public static final String DEFAULT_CRON="0 0 1 ? * L";
    /**
     * 每天10点触发一次
     **/
    public static final String DEFAULT_CRON = "0 0 1 * * ?";

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
        JobDetail jobDetail = JobBuilder.newJob(BgPictureJob.class).withIdentity(JOB1, GROUP1).usingJobData("myFloatValue", 1f).usingJobData("myStringValue", "gg").build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(JOB1, GROUP1)
                .withSchedule(cronScheduleBuilder).build();
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
     * 恢复某个任务
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