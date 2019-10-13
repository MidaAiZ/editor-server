package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.UserDAO;
import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.exception.MyException;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.pojo.UserExample;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.ShiroUtils;
import com.mida.chromeext.modules.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
     *
     * @param id
     * @return user
     */
    public User getUserById(int id) {
        return userDAO.selectByPrimaryKey(id);
    }

    /**
     * 通过邮箱获取User，用于登录，可能会改
     *
     * @param email 邮箱
     * @return User 对象
     * @author lihaoyu
     * @date 2019/10/10 19:09
     */
    public User getUserByEmail(String email) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<User> users = userDAO.selectByExample(example);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(NumConst.NUM0);
    }

    /**
     * 用户登录
     *
     * @param loginUser 登录的用户
     * @return
     */
    public User login(User loginUser) throws MyException {
        if (StringUtils.isEmpty(loginUser.getEmail())) {
            throw new MyException("登陆邮箱不能为空!");
        }
        if (StringUtils.isEmpty(loginUser.getPassword())) {
            throw new MyException("登陆密码不能为空!");
        }
        User user = getUserByEmail(loginUser.getEmail());
        if (user == null) {
            throw new MyException("登陆用户名或密码错误");
        }
        //密码错误
        if (!user.getPassword().equals(ShiroUtils.EncodeSalt(loginUser.getPassword(), user.getSalt()))) {
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
     * 验证用户邮箱是否已存在（数据库默认字段内容不区分大小写）
     *
     * @param email 邮箱
     * @return true 已存在
     * @author lihaoyu
     * @date 2019/10/8 19:00
     */
    public boolean existUserEmail(String email) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<User> users = userDAO.selectByExample(example);
        return !users.isEmpty();
    }


    /**
     * 用户注册，有校验。返回的user置空敏感字段
     *
     * @param preValidationUser
     * @return Result对象
     * @author lihaoyu
     * @date 2019/9/17 14:14
     */
    @Transactional(rollbackFor = Exception.class)
    public User register(User preValidationUser) throws BaseException {
        User user = UserValidation.validateRegistration(preValidationUser);
        if (existUserNumber(user.getNumber())) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_EXIST_NUMBER);
        }
        if (existUserEmail(user.getEmail())) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_EXIST_EMAIL);
        }
        user.setCountryCode(preValidationUser.getCountryCode());
        user.setSalt(UUID.randomUUID().toString());
        Date date = new Date();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        user.setPassword(ShiroUtils.EncodeSalt(user.getPassword(), user.getSalt()));
        user.setUid(userDAO.insertSelective(user));
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }


    public boolean changePwd(String srcPwd, String newPwd, User user) {
        if (!user.getPassword().equals(ShiroUtils.EncodeSalt(srcPwd, user.getSalt()))) {
            throw new BaseException(ExceptionEnum.USER_PASSWORD_INVALID);
        }
        if (!UserValidation.isPassWord(newPwd)) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_PASSWORD);
        }
        user.setPassword(ShiroUtils.EncodeSalt(newPwd, user.getSalt()));
        user.setUpdatedAt(new Date());
        userDAO.updateByPrimaryKeySelective(user);
        return true;
    }

    public boolean updateUserInfo(User preValidate) {
        User user = UserValidation.validateUpdate(preValidate);
        user.setUpdatedAt(new Date());
        userDAO.updateByPrimaryKeySelective(user);
        return true;
    }

}
