package net.tabplus.api.components.quartz.controller;

import net.tabplus.api.components.quartz.manager.QuartzManager;
import net.tabplus.api.components.shiro.RoleConstant;
import net.tabplus.api.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lihaoyu
 * @date 2019/10/6 15:38
 */
@RestController
@RequestMapping("quartz")
@ApiIgnore
@RequiresRoles(RoleConstant.SUPER_ROLE)
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    // 管理员权限

    @GetMapping("stop")
    public Result stop(@RequestParam String jobId, @RequestParam String groupId) throws SchedulerException {
        quartzManager.pauseJob(jobId, groupId);
        return Result.ok("stop success");
    }

    @GetMapping("resume")
    public Result resume(@RequestParam String jobId, @RequestParam String groupId) throws SchedulerException {
        quartzManager.resumeJob(jobId, groupId);
        return Result.ok("resume success");
    }

    @GetMapping("info")
    public Result getJobInfo(@RequestParam String jobId, @RequestParam String groupId) throws SchedulerException {
        String data = quartzManager.getJobInfo(jobId, groupId);
        return Result.ok("", data);
    }
}
