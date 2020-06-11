package com.myproject.springmybatis.util;

import com.myproject.springmybatis.model.User;
import com.myproject.springmybatis.util.bean.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 11:23
 */
public class ShiroUtils {
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout()
    {
        getSubject().logout();
    }

    public static User getUser()
    {
        User user = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new User();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }

    public static void setSysUser(User user)
    {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }


    public static Long getUserId()
    {
        return getUser().getId().longValue();
    }

    public static String getLoginName()
    {
//        return getUser().getLoginName();
        return null;
    }

    public static String getIp()
    {
        return getSubject().getSession().getHost();
    }

    public static String getSessionId()
    {
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }

    public static String MD5Pwd(String userName, String password) {
        //加密算法MD5
        //salt盐 userName+salt
        //迭代次数2
        String md5Pwd=new SimpleHash("MD5",password, ByteSource.Util.bytes(userName+"salt"),2).toHex();
        return md5Pwd;
    }

    public static void main(String[] args) {
        String pwd = MD5Pwd("张三","123456");
        System.out.println(pwd);
    }


}
