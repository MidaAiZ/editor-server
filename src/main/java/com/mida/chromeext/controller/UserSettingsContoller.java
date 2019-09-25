package com.mida.chromeext.controller;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.dto.DefaultUserSettingDto;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.pojo.UserSetting;
import com.mida.chromeext.service.UserSettingService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags="用户设置维护类")
@RequestMapping("user_settings")
public class UserSettingsContoller {

    @Autowired
    private UserSettingService userSettingService;

    @LoginRequired
    @GetMapping("profile")
    @ApiOperation(value = "获取用户配置，要求用户已登录", notes = "若返回空则表明用户无配置")
    public Result<DefaultUserSettingDto> show(@ApiIgnore @CurrentUser User user, @ApiIgnore HttpServletResponse response) {
        UserSetting userSetting = userSettingService.getUserSettingByUserId(user.getUid());
        if (userSetting == null) {
            try {
                response.sendRedirect("default");
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
        }
        DefaultUserSettingDto dd = JSONObject.parseObject(userSetting.getSettings(), DefaultUserSettingDto.class);
        return Result.ok(JSONObject.parseObject(userSetting.getSettings(), DefaultUserSettingDto.class));
    }

    @LoginRequired
    @PostMapping("profile")
    @ApiOperation(value = "设置或更新用户配置，要求用户已登录", notes = "返回插入的用户配置")
    public Result<DefaultUserSettingDto> createOrUpdate(@ApiIgnore @CurrentUser User user, @Validated @RequestBody DefaultUserSettingDto setting, BindingResult bindingResult) {
        UserSetting userSetting = new UserSetting();
        userSetting.setSettings(JSONObject.toJSONString(setting));
        Boolean suc = userSettingService.addUserSetting(user.getUid(), userSetting);
        return suc ? Result.ok(setting) : Result.error(bindingResult.getFieldError().getDefaultMessage());
    }

    @GetMapping("default")
    @ApiOperation(value="获取系统默认的用户配置", notes = "不要求用户登录")
    public Result<DefaultUserSettingDto> showDefault() {
        return Result.ok(new DefaultUserSettingDto());
    }
}
