package net.tabplus.api.modules.controller.app;

import net.tabplus.api.exception.MyException;
import net.tabplus.api.modules.pojo.User;
import net.tabplus.api.modules.service.UserService;
import net.tabplus.api.utils.JwtUtils;
import net.tabplus.api.utils.Result;
import net.tabplus.api.utils.ResultCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "前台用户登录注销接口", tags = "{}")
public class SessionController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("login")
    @ApiOperation(value = "用户登录接口", notes = "若登入成功，返回的data中包含令牌token、令牌过期时间expire、用户对象user，在下一次请求时需要将token加入请求头中（请求头中key为token，value为返回的token值）以实现登录状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @ApiResponse(code = 200, responseContainer = "token", message = "令牌")
    public Result login(@ApiIgnore User loginUser, @ApiIgnore HttpServletResponse response) {
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
        Cookie cookie = new Cookie(jwtUtils.getHeader(), token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) jwtUtils.getExpire());
        response.addCookie(cookie);

        return Result.ok(map);
    }

    /**
     * 用户登出接口
     *
     * @return
     */
    @PostMapping("logout")
    public Result logout(@ApiIgnore HttpServletResponse response) {
        Cookie cookie = new Cookie(jwtUtils.getHeader(), null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return Result.ok();
    }
}
