package com.mida.chromeext.modules.controller.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.modules.validation.UserValidation;
import com.mida.chromeext.modules.vo.MngUserListQueryVo;
import com.mida.chromeext.utils.Result;

/**
 * 管理员对用户的操作
 * @author lihaoyu
 * @date 2019/10/17 14:53
 */
@RestController("mngUserController")
@RequestMapping("manage/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    Result<List<User>> listUser(MngUserListQueryVo queryVo){
        List<User> users;
        try {
            users = userService.listUserByMng(queryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败");
        }
        return Result.ok(users);
    }

    @GetMapping("get")
    Result<User> getUser(Integer userId){
       return Result.ok(userService.getUserById(userId));
    }

    @PostMapping("changePwd")
    Result changePwd(Integer userId, String pwd){
        if(!UserValidation.isPassWord(pwd)) {
           throw new BaseException(ExceptionEnum.USER_REGISTER_PASSWORD);
        }
        userService.changePwdByMng(userId, pwd);
        return Result.ok("200","修改密码成功");
    }

}
