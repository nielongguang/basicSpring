package com.cn.study.basicSpring.component.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;



/**
 * Created by 96342 on 2017/7/8.
 *  *1.创建SecurityManager ，加载配置文件，并把该类交付给SecurityUtils管理
 * 2.创建subject主体，即需要权限验证的主体
 * 3.使用 令牌为参数，作为主体参数验证
 */
public class ShiroTest {


    @Test
    public void testHelloworld() {
//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager,并且交付给utils管理
        //此處需要注意SecurityManager是由shiro提供的，不是lang包下的
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂获取shiro的SecurityManager实例
        SecurityManager securityManager= factory.getInstance();
        //此方法为静态方法，不需要new出实例，可以直接采用 将近SecurityManager交付给Utils
        SecurityUtils.setSecurityManager(securityManager);
        //创建用来交互的主体
        //注意，同一个主体，只要有一个验证不通过，就全部不通过，可能是因为这是同一个角色的验证。
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
        UsernamePasswordToken token1 = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
            Assert.assertTrue(!subject.isAuthenticated());
            subject.login(token1);
        } catch (AuthenticationException e) {
            //身份 验证失败，这里理应跳转到错误页面...
        }
        Assert.assertTrue(!subject.isAuthenticated());
        subject.logout();


    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    @Test
    public void JDBCtext() {
        //1.创建SecurityManager工厂，并加载实例
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager =factory.getInstance();
        //2.將 安全管理實例交付給 安全管理抽象
        SecurityUtils.setSecurityManager(securityManager);
        //3.通过安全管理工具的静态方法加载subject 此方法 自动绑定到线程
        Subject subject= SecurityUtils.getSubject();
        //4.创建登录令牌，此处默认为 主体+密码模式，主体为用户名
        UsernamePasswordToken  token =new UsernamePasswordToken("zhang","123");
        //5.断言登录成功
        try {
            subject.login(token);
        } catch (AuthenticationException e) {

        }
        //isAutehticated表示已经验证
        Assert.assertTrue(subject.isAuthenticated());
        //6.注销登录
        subject.logout();
    }
}
