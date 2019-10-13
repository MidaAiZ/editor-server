package com.mida.chromeext.components.quartz.controller;

import com.mida.chromeext.components.quartz.manager.QuartzManager;
import com.mida.chromeext.utils.Result;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lihaoyu
 * @date 2019/10/6 15:38
 */
@RestController
@RequestMapping("quartz")
@ApiIgnore
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    // 管理员权限

    @GetMapping("stop")
    public Result stop() throws SchedulerException {
        quartzManager.pauseJob(QuartzManager.JOB1, QuartzManager.GROUP1);
        return Result.ok("stop success");
    }

    @GetMapping("resume")
    public Result resume() throws SchedulerException {
        quartzManager.resumeJob(QuartzManager.JOB1, QuartzManager.GROUP1);
        return Result.ok("resume success");
    }

    @GetMapping("info")
    public Result getJobInfo() throws SchedulerException {
        String data = quartzManager.getJobInfo(QuartzManager.JOB1, QuartzManager.GROUP1);
        return Result.ok("", data);
    }
}
