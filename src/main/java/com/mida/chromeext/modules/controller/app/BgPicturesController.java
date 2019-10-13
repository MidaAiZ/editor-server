package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.modules.pojo.BgPicture;
import com.mida.chromeext.modules.service.BgPictureService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lihaoyu
 * @date 2019/10/7 15:11
 */
@RestController
@RequestMapping("bg_pictures")
@Api(value = "背景图片维护", tags = "{}")
public class BgPicturesController {
    @Autowired
    BgPictureService bgPictureService;

    /**
     * 随机获取数据库中的一个背景图片url
     *
     * @return 单个BgPicture的URL
     * @author lihaoyu
     * @date 2019/10/7 15:14
     */
    @GetMapping(value = "random-one")
    public Result<String> getBgPicture() {
        BgPicture randomBgPicture = bgPictureService.getRandomBgPicture();
        String src = randomBgPicture.getSrc();
        return Result.ok("", src);
    }

}
