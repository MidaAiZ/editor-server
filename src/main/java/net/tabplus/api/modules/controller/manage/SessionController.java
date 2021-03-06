package net.tabplus.api.modules.controller.manage;

import com.alibaba.fastjson.JSON;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.service.AdminService;
import net.tabplus.api.utils.Result;
import net.tabplus.api.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController("mngSessionController")
@Api(value = "后台管理员登录操作接口", tags = "{}")
@RequestMapping("manage")
public class SessionController {
    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    @ApiOperation("管理员登录")
    public Result<Admin> login(@RequestParam String number, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(number, password);
        token.setRememberMe(true);
        try {
            SecurityUtils.getSubject().login(token);
            Subject subject = SecurityUtils.getSubject();
            return Result.ok(JSON.parseObject(JSON.toJSON(subject.getPrincipal()).toString(), Admin.class));
        } catch (AuthenticationException uae) {
            return Result.error(ResultCode.FAIL.code(), "error number or password");
        } catch (Exception e) {
            return Result.error(ResultCode.FAIL.code(), "unkonwn error");
        }
    }

    @PostMapping(value = "logout")
    @ApiOperation("退出登录")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return Result.ok();
    }

    @ApiIgnore
    @GetMapping("403")
    public Result authFail() {
        return Result.error(ResultCode.FORBIDDEN.code());
    }
}
