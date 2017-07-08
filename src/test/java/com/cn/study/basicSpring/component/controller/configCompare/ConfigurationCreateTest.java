package com.cn.study.basicSpring.component.controller.configCompare;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-27
 * <p>Version: 1.0
 */
public class ConfigurationCreateTest {

    static {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro/shiro-config.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        //将SecurityManager设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);
    }

    private Subject subject  =  SecurityUtils.getSubject();

    @Test
    public void test() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
         subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }

    @After
    public void after() {
        ThreadContext.unbindSubject();
    }
}
