package com.mida.chromeext.components.quartz.job;

import com.mida.chromeext.components.quartz.service.BgPictureJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lihaoyu
 * @date 2019/10/6 14:05
 */
@Slf4j
@Component
@PersistJobDataAfterExecution //持久化JobDataMap里的数据，使下一个定时任务还能获取到这些值
@DisallowConcurrentExecution //禁止并发多任务执行，所以永远只有一个任务在执行中
public class BgPictureJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BgPictureJobService bgPictureJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("壁纸获取任务开始");
        executeTask();
        log.info("壁纸获取任务执行结束");
    }

    public void executeTask() {
//        bgPictureJobService.addBgPictures();
        System.out.println("定时任务" + new Date());
    }

}
