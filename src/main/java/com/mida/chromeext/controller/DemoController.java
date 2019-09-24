package com.mida.chromeext.controller;

import com.mida.chromeext.utils.LocaleUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo")
public class DemoController {
    @GetMapping("")
    public String demoMethod() {
        return LocaleUtils.getMsg("welcome");
    }
}
