package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.User;
import com.mida.chromeext.service.UserService;
import com.mida.chromeext.utils.JwtUtils;
import com.mida.chromeext.exception.MyException;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用户登录API接口
     * @param number 用户账号
     * @param password 用户密码
     * @param imgCode 图片验证码
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Map params) {
        User loginUser = new User();
        loginUser.setNumber((String)params.get("number"));
        loginUser.setPassword((String)params.get("password"));

        try {
           loginUser = userService.login(loginUser);
        } catch (MyException e) {
            Result r = Result.error(ResultCode.FAIL.code(), e.getMessage());
            return r;
        }

        // 生成token
        String token = jwtUtils.generateToken(loginUser.getUid().toString());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("user", loginUser);
        return Result.ok(map);
    }

    /**
     * 用户登出接口
     * @return
     */
    @PostMapping("logout")
    public Result logout() {
        // 接口写着玩，前端删除token即可
        return Result.ok();
    }
}
