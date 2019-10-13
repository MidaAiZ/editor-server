package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.utils.LocaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo")
@Api(value = "Demo", tags = "{}")
public class DemoController {
    @GetMapping("")
    @ApiOperation("demo接口，可以通过lang参数测试本地化")
    public String demoMethod(@RequestParam(required = false) String lang) {
        return LocaleUtils.getMsg("welcome");
    }
}
