package com.mida.chromeext.shiro;

import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.Permission;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.service.AdminService;
import com.mida.chromeext.service.PermissionService;
import com.mida.chromeext.service.RoleService;
import com.mida.chromeext.utils.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类的功能描述.
 * shiro 认证
 *
 * @Auther hxy
 * @Date 2017/4/27
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin admin = (Admin) principals.getPrimaryPrincipal();
        if (admin != null) {
            //根据用户id查询该用户所有的角色,并加入到shiro的SimpleAuthorizationInfo
            List<Role> roles = roleService.getRolesByAdminId(admin.getAid());
            Set<String> permissions = new HashSet<>();
            for (Role role : roles) {
                info.addRole(role.getName());
                for (Permission p : role.getPermissions()) {
                    permissions.add(p.getPermision());
                }
            }
            // TODO 超级管理员拥有最高权限
            // 将权限添加到info
            info.addStringPermissions(permissions);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String number = (String) token.getPrincipal();
        Admin admin = adminService.getAdminByNumber(number);
        System.out.println(admin);
        if (admin == null) {
            throw new AuthenticationException("帐号密码错误");
        }
        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), getName());
        return saInfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.algorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
