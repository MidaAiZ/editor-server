package com.mida.chromeext.controller;

import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.service.UserService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public Result register(@RequestBody Map params) {
        User regUser = new User();
        regUser.setNumber((String)params.get("number"));
        regUser.setPassword((String)params.get("password"));
        regUser.setEmail((String)params.get("email"));
        try {
            regUser = userService.register(regUser);
        } catch (BaseException e) {
            return Result.error(e.getCode(), e.getMessage());
        }

        return Result.ok().put("user", regUser);
    }


    @RequestMapping("")
    public Result update(@RequestParam String number, @RequestParam String password, @RequestParam String email) {
       return Result.ok();
    }
}
