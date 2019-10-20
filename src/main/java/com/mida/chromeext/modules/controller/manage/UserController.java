package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.modules.validation.UserValidation;
import com.mida.chromeext.modules.vo.MngUserListQueryVo;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员对用户的操作
 *
 * @author lihaoyu
 * @date 2019/10/17 14:53
 */
@RestController("mngUserController")
@RequestMapping("manage/users")
@Api(value = "后台用户相关接口", tags = "提供用户管理和统计相关的 Rest API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emailList", value = "email列表", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodeList", value = "国家码列表", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userIdList", value = "用户Id列表", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    Result<List<User>> listUsers(MngUserListQueryVo queryVo) {
        List<User> users;
        try {
            users = userService.listUserByMng(queryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败");
        }
        return Result.ok(users);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "query")
    Result<User> getUser(@PathVariable Integer userId) {
        return Result.ok(userService.getUserById(userId));
    }

    @PutMapping("{id}/password")
    @ApiOperation(value = "根据id修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", dataType = "String", paramType = "query")})
    Result changePwd(@PathVariable Integer userId, String pwd) {
        if (!UserValidation.isPassWord(pwd)) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_PASSWORD);
        }
        userService.changePwdByMng(userId, pwd);
        return Result.ok("200", "修改密码成功");
    }

}
