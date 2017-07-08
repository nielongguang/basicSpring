#shiro

[TIC]

##簡介
API:  
 
**Authentication**：
```身份认证/登录。验证用户是否拥有指定的地身份。一般流程是SecurityUtils加载SecurityManager，SecurityManager加载Realm（域，这里应该代替的是角色权限范围的意思），然后SecurityManager将认证功能托付付Authentication. Subject的login功能将启动认证/登录```


**Authorization**： ```  授权,即判断用户是否能做事情，常见的如，判断某个用户是否拥有某个角色或者细粒度的验证某个用
                                                    户对某个资源是否具有某个权限；
Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；```

**Cryptography**：```加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；```


**Web Support：Web** 支持，可以非常容易的集成到Web 环境；

**Caching**：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率

**Concurrency**：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能
把权限自动传播过去；


**Testing**：提供测试支持；


**Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；


**Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录
了。

**SecurityManager**:接口继承了Authenticator

**Authenticator**:有一个ModularRealmAuthenticator实现，
其委托给多个Realm 进行验证，验证规则通过AuthenticationStrategy 接口指定，默认提供
的实现：


1.FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证
成功的认证信息，其他的忽略；


2.AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy
不同，返回所有Realm身份验证成功的认证信息；


3.AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的
认证信息，如果有一个失败就失败了。  
 **ModularRealmAuthenticator**默认使用**AtLeastOneSuccessfulStrategy**策略
 
 ```
#指定securityManager的authenticator实现
authenticator=org.apache.shiro.authc.pam.ModularRealmAuthenticator
securityManager.authenticator=$authenticator
#指定securityManager.authenticator的authenticationStrategy
allSuccessfulStrategy=org.apache.shiro.authc.pam.AllSuccessfulStrategy
securityManager.authenticator.authenticationStrategy=$allSuccessfulStrategy
```

##授权

Shiro 支持三种方式的授权： 
```$xslt
编程式：通过写if/else 授权代码块完成 
 ```
 ```
Subject subject = SecurityUtils.getSubject();
if(subject.hasRole(“admin”)) {
//有权限
} else {
//无权限
}
```
注解式：通过在执行的Java方法上放置相应的注解完成   
```
@RequiresRoles("admin")
public void hello() {
//有权限
}
```

JSP/GSP 标签：在JSP/GSP

```jsp
<shiro:hasRole name="admin">
<!— 有权限—>
</shiro:hasRole>
页面通过相应的标签完成
```


没有权限将抛出相应的异常；