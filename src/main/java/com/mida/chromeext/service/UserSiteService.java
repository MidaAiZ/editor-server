package com.mida.chromeext.service;

import com.mida.chromeext.dao.UserSiteDAO;
import com.mida.chromeext.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户网站关联服务
 * @author lihaoyu
 * @date 2019/9/17 12:26
 */
@Service
public class UserSiteService {

    @Autowired
    private UserSiteDAO userSiteDAO;

    public Result addSiteByUserIdAndSiteId(Integer userId, Integer siteId){




        return null;
    }


}
