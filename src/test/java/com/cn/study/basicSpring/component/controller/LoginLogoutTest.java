package com.cn.study.basicSpring.component.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 96342 on 2017/7/7.
 * 1.创建SecurityManager ，加载配置文件，并把该类交付给SecurityUtils管理
 * 2.创建subject主体，即需要权限验证的主体
 * 3.使用 令牌为参数，作为主体参数验证
 *
 */

public class LoginLogoutTest {
    @Test
    public void testHelloworld() {
//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
//      new IniSecurityManagerFactory("classpath:shiro.ini");
        new IniSecurityManagerFactory("classpath:shiro-realm.ini");

//2、得到SecurityManager实例并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
//4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
//5、身份验证失败
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
//6、退出
        subject.logout();
    }

/**
  org.apache.shiro.realm.jdbc.JdbcRealm：
 通过sql查询相应的信息，如
 “select password from users where username = ?”获取用户密码，
 “select password, password_salt from users where username = ?”获取用户密码及盐；
 “select role_name from user_roles where username = ?” 获取用户角色；
 “select permission from roles_permissions where role_name = ?”获取角色对应的权限信息；也可以调用相应的api进行自定义sql；
 */


    @Test
    public void testJDBCRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }

    private void logon(String configFile,String username,String password) {
        //1.获取secutiryManager 工厂
        // 此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);
        //2.得到SecutiryManaer实例，并绑定给SecutiryUtils
         SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到subject以及创建用户名/密码验证令牌 token
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }


    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    @Test
    public void  testAllSuccessfulStrategyWithSuccess() {
        logon("classpath:shiro/shiro-authenticator-all-success.ini","zhang","123");
        Subject subject=SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());

    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {

        logon("classpath:shiro/shiro-authenticator-all-fail.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
    }



}
