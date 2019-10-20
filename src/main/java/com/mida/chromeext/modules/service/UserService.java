package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.exception.MyException;
import com.mida.chromeext.modules.dao.UserDAO;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.pojo.UserExample;
import com.mida.chromeext.modules.validation.UserValidation;
import com.mida.chromeext.modules.vo.MngUserListQueryVo;
import com.mida.chromeext.modules.vo.statistic.StatisticCountVo;
import com.mida.chromeext.modules.vo.statistic.CountryUsersCount;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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

    /**
     * 管理员查询用户
     *
     * @param queryVo 复合查询条件
     * @return List<User>
     * @author lihaoyu
     * @date 2019/10/17 15:40
     */
    public List<User> listUserByMng(MngUserListQueryVo queryVo) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!CollectionUtils.isEmpty(queryVo.getUserIdList())) {
            criteria.andUidIn(queryVo.getUserIdList());
        }
        if (!CollectionUtils.isEmpty(queryVo.getEmailList())) {
            criteria.andEmailIn(queryVo.getEmailList());
        }
        if (!CollectionUtils.isEmpty(queryVo.getCountryCodeList())) {
            criteria.andCountryCodeIn(queryVo.getCountryCodeList());
        }
        if (queryVo.getBeginTime() != null) {
            criteria.andCreatedAtGreaterThan(queryVo.getBeginTime());
        }
        if (queryVo.getEndTime() != null) {
            criteria.andCreatedAtLessThan(queryVo.getEndTime());
        }
        PageHelper.startPage(queryVo);
        List<User> users = userDAO.selectByExample(example);
        return users;
    }

    public void changePwdByMng(Integer userId, String pwd) {
        User user = new User();
        String salt = userDAO.selectByPrimaryKey(userId).getSalt();
        user.setPassword(ShiroUtils.EncodeSalt(pwd, salt));
        user.setUid(userId);
        userDAO.updateByPrimaryKeySelective(user);
    }

    /**
     * 从登录记录中查询某段时间的登录人数
     *
     * @param beginDate 开始时间必填
     * @param endDate   可以为空
     * @return 统计结果map    key是某天，value是人数
     * @author lihaoyu
     * @date 2019/10/19 16:12
     */
    public Map<String, Long> listDailyAliveUsersCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = userDAO.listDailyAliveUsersCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        for (StatisticCountVo vo : voList) {
            resMap.put(vo.getName(), vo.getCount());
        }
        return resMap;
    }

    /**
     * 同上 月统计
     *
     * @param beginDate
     * @param endDate
     * @return 同上
     * @author lihaoyu
     * @date 2019/10/19 16:23
     */
    public Map<String, Long> listMonthlyAliveUsersCount(Date beginDate, Date endDate) {
        // mybatis 返回的Map是每一行中多个列组成的map
        List<StatisticCountVo> voList = userDAO.listMonthlyAliveUsersCount(beginDate, endDate);
        Map<String, Long> resMap = new HashMap<>(NumConst.NUM16);
        voList.forEach(vo -> resMap.put(vo.getName(), vo.getCount()));
        return resMap;
    }

    /**
     * 统计所有国家的用户数
     *
     * @return 统计结果map    key是某国，value是人数
     * @author lihaoyu
     * @date 2019/10/19 17:40
     */
    public List<CountryUsersCount> listUsersCountByCountry() {
        return userDAO.listUsersCountByCountry();
    }

    /**
     * 统计所有用户量
     *
     * @return 总数
     * @author lihaoyu
     * @date 2019/10/19 21:39
     */
    public Long getAllUsersCount() {
        return userDAO.countByExample(new UserExample());
    }
}
