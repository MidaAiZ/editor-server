package com.mida.chromeext.quartz.job;

import com.mida.chromeext.quartz.service.BgPictureJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
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
public class BgPictureJob implements Job, Serializable {

    @Autowired
    private BgPictureJobService bgPictureJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("壁纸获取任务开始");
//      BgPictureJobService bgPictureJobService =
//      (BgPictureJobService) SpringContextUtils.getBean("bgPictureJobService");
        executeTask();
        log.info("壁纸获取任务执行结束");
    }

    public void executeTask(){
        bgPictureJobService.addBgPictures();
        Date date = new Date();
        System.out.println("定时任务" + date);
    }

}
