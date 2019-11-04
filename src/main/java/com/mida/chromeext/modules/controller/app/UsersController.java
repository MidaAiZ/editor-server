package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.utils.LocaleHelper;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("users")
@Api(value = "前台用户操作接口", tags = "提供用户相关的 Rest API")
public class UsersController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCode", value = "国家码", defaultValue = "CN", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tel", value = "电话", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "telPrefix", value = "电话前缀", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gender", defaultValue = "0", value = "性别", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "occupation", value = "职业", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query"),})
    public Result<User> register(@ApiIgnore @Validated User regUser, HttpServletRequest request) {
        // 如果没填国家码
        if (StringUtils.isEmpty(regUser.getCountryCode())) {
            regUser.setCountryCode(LocaleHelper.getContextCountryCode(request));
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

    @LoginRequired
    @PutMapping("password")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true, dataType = "String", paramType = "query"),})
    public Result changePwd(@RequestParam("oldPwd") String srcPwd, @RequestParam String newPwd,
                            @ApiIgnore @CurrentUser User user) {
        boolean flag = userService.changePwd(srcPwd, newPwd, user);
        if (flag == false) {
            return Result.error("旧密码错误");
        }
        return Result.ok();
    }

    @LoginRequired
    @PutMapping("profile")
    @ApiOperation("修改用户信息,前端先填充好，让用户修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gender", value = "性别", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "tel", value = "手机", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "prefix", value = "手机前缀", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "occupation", value = "职业", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "国家码", dataType = "String", paramType = "query"),
    })
    public Result updateUserInfo(Byte gender, String tel, String prefix, String occupation, String code,
                                 @ApiIgnore @CurrentUser User user) throws BaseException {
        User preValidation = new User();
        preValidation.setCountryCode(code);
        preValidation.setTel(tel);
        preValidation.setGender(gender);
        preValidation.setTelPrefix(prefix);
        preValidation.setOccupation(occupation);
        preValidation.setUid(user.getUid());
        try {
            userService.updateUserInfo(preValidation);
        } catch (BaseException e) {
            return Result.error(e.getMessage());
        }
        return Result.ok();
    }
}
