package com.mida.chromeext.service;

import com.mida.chromeext.dao.BgPictureDAO;
import com.mida.chromeext.pojo.BgPicture;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 15:29
 */
@Service
public class BgPictureService {

    @Autowired
    BgPictureDAO bgPictureDAO;

    public BgPicture getRandomBgPicture() {
        List<BgPicture> list = bgPictureDAO.listRandomBgPicture(NumConst.NUM1);
        return list.get(NumConst.NUM0);
    }

}
