package com.mida.chromeext.controller;

import java.util.List;

<<<<<<< HEAD
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> site search
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.service.SiteService;
<<<<<<< HEAD
import com.mida.chromeext.vo.SiteListQueryVo;
=======
import com.mida.chromeext.vo.SiteQueryVo;
>>>>>>> site search

/**
 * @author lihaoyu
 * @date 2019/9/24 18:50
 */
@RestController
@RequestMapping("sites")
public class SiteController {

    @Autowired
    SiteService siteService;

    @GetMapping("")
public List<Site> listSitesByPage(@Validated @ApiParam("疯了") @RequestBody  SiteListQueryVo queryVo, BindingResult result){
        if(result.hasErrors()) {
            return null;
        }
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return sites;
    }


}
