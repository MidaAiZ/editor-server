package com.mida.chromeext.service;

import com.mida.chromeext.dao.UserDAO;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.pojo.UserExample;
import com.mida.chromeext.utils.MyException;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
     * @param userNumber 用户账号
     * @return 用户po或者null
     * @author lihaoyu
     * @date 2019/9/17 12:03
     */
    public User getUserByNumber(String userNumber) {
        UserExample example = new UserExample();
        example.createCriteria().andNumberEqualTo(userNumber);
        List<User> users = userDAO.selectByExample(example);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(NumConst.NUM0);
    }

    /**
     * 根据ID查询用户
     * @param id
     * @return user
     */
    public User getUserById(int id) {
        return  userDAO.selectByPrimaryKey(id);
    }

    /**
     * 用户登录
     * @param loginUser 登录的用户
     * @return
     */
    public User login(User loginUser) throws MyException {
        if(StringUtils.isEmpty(loginUser.getNumber())){
            throw new MyException("登陆用户名不能为空!");
        }
        if(StringUtils.isEmpty(loginUser.getPassword())){
            throw new MyException("登陆密码不能为空!");
        }
        User user = getUserByNumber(loginUser.getNumber());
        if(user == null){
            throw new MyException("登陆用户名或密码错误");
        }
        //密码错误
        if(!user.getPassword().equals(ShiroUtils.EncodeSalt(user.getPassword(), user.getSalt()))){
            throw new MyException("登陆用户名或密码错误");
        }
        return user;
    }

    /**
     * 验证用户名是否已存在（数据库默认字段内容不区分大小写）
     *
     * @param userNumber 用户账号
     * @return true 用户名已存在
     * @author lihaoyu
     * @date 2019/9/17 13:48
     */
    public boolean existUserNumber(String userNumber) {
        UserExample example = new UserExample();
        example.createCriteria().andNumberEqualTo(userNumber);
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
    public Result registerUser(User user) {

        return null;
    }

}
