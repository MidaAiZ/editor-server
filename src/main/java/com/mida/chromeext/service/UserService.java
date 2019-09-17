package com.mida.chromeext.service;

import java.util.List;

import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.chromeext.dao.UserDAO;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.pojo.UserExample;

/**
 * @author lihaoyu
 * @date 2019/9/15 16:19
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户po或者null
     * @author lihaoyu
     * @date 2019/9/17 12:03
     */
    public User getUserByName(String userName){
        UserExample example = new UserExample();
        example.createCriteria().andNumberEqualTo(userName);
        List<User> users = userDAO.selectByExample(example);
        if(users.isEmpty()){
            return null;
        }
        return users.get(NumConst.NUM0);
    }

    /**
     * 验证用户名是否已存在（数据库默认字段内容不区分大小写）
     *
     * @param userName 用户名
     * @return true 用户名已存在
     * @author lihaoyu
     * @date 2019/9/17 13:48
     */
    public boolean existUserName(String userName){
        UserExample example = new UserExample();
        example.createCriteria().andNumberEqualTo(userName);
        List<User> users = userDAO.selectByExample(example);
        return !users.isEmpty();
    }

    /**
     * 用户注册，校验。未填字段 created_at,updated_at,
     *
     * @param user
     * @return Result对象
     * @author lihaoyu
     * @date 2019/9/17 14:14
     */
    public Result registerUser(User user){



        return null;
    }









}
