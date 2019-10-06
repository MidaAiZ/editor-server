package com.mida.chromeext.controller;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.service.UserService;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("users")
@Api(tags = "前台用户操作接口", description = "提供用户相关的 Rest API")
public class UsersController {
    @Autowired
    UserService userService;

    @PostMapping("")
    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query"),
    })
    public Result<User> register(@ApiIgnore @Validated User regUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        User user;
        try {
            user = userService.register(regUser);
        } catch (BaseException e) {
            return Result.error(e.getCode(), e.getMessage());
        }
        return Result.ok(user);
    }

    @LoginRequired
    @GetMapping("profile")
    @ApiOperation("获取当前已登录用户信息，可以判断用户是否已登录")
    public Result<User> profile(@ApiIgnore @CurrentUser User user) {
        return user == null ? Result.error(ResultCode.NOT_LOGIN.code(), ResultCode.NOT_LOGIN.code()) : Result.ok(user);
    }
}
