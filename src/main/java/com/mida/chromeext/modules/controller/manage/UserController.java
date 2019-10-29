package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.modules.validation.UserValidation;
import com.mida.chromeext.modules.vo.ListResultVo;
import com.mida.chromeext.modules.vo.MngUserListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @GetMapping("")
    @RequiresPermissions(PermisConstant.SHOW_USER)
    @ApiOperation(value = "分页获取系统已注册用户列表", notes = "支持通过条件筛选用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIdList", value = "用户ID列表", dataType = "String", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "emailList", value = "用户邮箱列表", dataType = "String", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "countryCodeList", value = "用户所属国家（地区）码", dataType = "Integer", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "createdBefore", value = "用户注册时间<=, 格式yyyy-MM-dd HH:mm:ss", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "createdAfter", value = "用户注册时间>=，格式yyyy-MM-dd HH:mm:ss", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    public Result<ListResultVo<User>> listUser(@ApiIgnore  MngUserListQueryVo queryVo) {
        return Result.ok(userService.listUserByMng(queryVo));
    }

    @GetMapping("{uid}")
    @ApiOperation(value = "通过用户ID查看用户信息")
    @RequiresPermissions(PermisConstant.SHOW_USER)
    public Result<User> getUser(@PathVariable Integer uid) {
        User user = userService.getUserById(uid);
        return user != null ? Result.ok(user) : Result.error(ResultCode.NOT_FOUND.code(), "No such user with ID = " + uid.toString());
    }

    @PutMapping("{uid}/password")
    @ApiOperation("修改用户密码")
    @RequiresPermissions(PermisConstant.MODIFY_USER)
    public Result<Boolean> changePwd(@PathVariable Integer uid, @RequestParam String password) {
        if(!UserValidation.isPassWord(password)) {
           throw new BaseException(ExceptionEnum.USER_REGISTER_PASSWORD);
        }
        return userService.changePwdByMng(uid, password) ? Result.ok(true) : Result.error();
    }
}
