package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.service.AdminService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("管理员登录操作接口")
@RequestMapping("manage")
public class AdminSessionController {
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

            return Result.ok();
        } catch ( AuthenticationException uae ) {
            return Result.error(ResultCode.FAIL.code(), "error number or password");
        } catch ( Exception e) {
            return Result.error(ResultCode.FAIL.code(), "unkonwn error");
        }
    }

}
