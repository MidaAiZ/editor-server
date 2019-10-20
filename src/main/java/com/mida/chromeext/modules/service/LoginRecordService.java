package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.LoginRecordDAO;
import com.mida.chromeext.modules.pojo.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihaoyu
 * @date 2019/10/17 16:55
 */
@Service
public class LoginRecordService {

    @Autowired
    LoginRecordDAO loginRecordDAO;


    public void addRecord(LoginRecord record) {
        loginRecordDAO.insertSelective(record);
    }


}
