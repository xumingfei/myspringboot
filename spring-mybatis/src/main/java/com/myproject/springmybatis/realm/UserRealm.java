package com.myproject.springmybatis.realm;

import com.myproject.springmybatis.model.User;
import com.myproject.springmybatis.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 9:44
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> strSet = new HashSet<>();
        strSet.add("user:show");
        strSet.add("user:admin");
        info.setStringPermissions(strSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        String password = userService.getPassword(userName);
        User user = userService.getUserByLoginName(userName);
        if (StringUtils.isEmpty(userName)) {
            throw new AccountException("用户名不正确");
        }
        if (StringUtils.isEmpty(userPwd)) {
            throw new AccountException("密码不正确");
        }
        System.out.println(getName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUserName()+"salt"), getName());
    }


}
