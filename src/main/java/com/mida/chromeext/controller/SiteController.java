package com.mida.chromeext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.service.SiteService;
import com.mida.chromeext.vo.SiteQueryVo;

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
public List<Site> listSitesByPage(SiteQueryVo queryVo){
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return sites;

    }


}
