package com.cn.study.basicSpring.component.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


/**
 * Created by 96342 on 2017/7/8.
 */
public class AuthorizationTest {

    private void load(String iniResourcePath) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniResourcePath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
    }

    private void login(String configFile,String username,String password) {
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


    @Test
    public void testHasRole() {
        login("classpath:shiro/shiro-role.ini", "zhang", "123");
//判断拥有角色：role1
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.hasRole("role1"));
//判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
//判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    private Subject subject() {
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        login("classpath:shiro/shiro-role.ini", "zhang", "123");
//断言拥有角色：role1
        subject().checkRole("role1");
//断言拥有角色：role1 and role3 失败抛出异常
        subject().checkRoles("role1", "role3");
    }

    @Test
    public void testIsPermitted() {
        login("classpath:shiro/shiro-permission.ini", "zhang", "123");
//判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
//判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
//判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        login("classpath:shiro/shiro-permission.ini", "zhang", "123");
//断言拥有权限：user:create
        subject().checkPermission("user:create");
//断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
//断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }

}
