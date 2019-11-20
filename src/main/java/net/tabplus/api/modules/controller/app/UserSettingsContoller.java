package net.tabplus.api.modules.controller.app;

import com.alibaba.fastjson.JSONObject;
import net.tabplus.api.annotation.CurrentUser;
import net.tabplus.api.annotation.LoginRequired;
import net.tabplus.api.modules.pojo.User;
import net.tabplus.api.modules.service.UserSettingService;
import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.dto.DefaultUserSettingDto;
import net.tabplus.api.modules.pojo.UserSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "前台用户设置维护类", tags = "{}")
@RequestMapping("user_settings")
public class UserSettingsContoller {

    @Autowired
    private UserSettingService userSettingService;

    @Autowired
    private ApplicationContext applicationContext;

    @LoginRequired
    @GetMapping("profile")
    @ApiOperation(value = "获取用户配置，要求用户已登录", notes = "用户没有个性化配置的情况下返回默认配置")
    public Result<DefaultUserSettingDto> show(@ApiIgnore @CurrentUser User user) {
        UserSetting userSetting = userSettingService.getUserSettingByUserId(user.getUid());
        DefaultUserSettingDto dd = userSetting == null ? applicationContext.getBean(DefaultUserSettingDto.class) : JSONObject.parseObject(userSetting.getSettings(), DefaultUserSettingDto.class);
        return Result.ok(dd);
    }

    @LoginRequired
    @PostMapping("profile")
    @ApiOperation(value = "设置或更新用户配置，要求用户已登录", notes = "上传需要保存的配置字段，如果该字段为复合对象（JSON），请提交完整字段对象，否则缺失的子对象字段会被覆盖丢失。返回插入的用户配置")
    public Result<DefaultUserSettingDto> createOrUpdate(@ApiIgnore @CurrentUser User user, @Validated @RequestBody DefaultUserSettingDto setting, BindingResult bindingResult) {
        DefaultUserSettingDto res = userSettingService.addUserSetting(user.getUid(), setting);
        return res != null ? Result.ok(res) : Result.error(bindingResult.getFieldError().getDefaultMessage());
    }

    @GetMapping("default")
    @ApiOperation(value = "获取系统默认的用户配置", notes = "不要求用户登录")
    public Result<DefaultUserSettingDto> showDefault() {
        return Result.ok(applicationContext.getBean(DefaultUserSettingDto.class));
    }
}
