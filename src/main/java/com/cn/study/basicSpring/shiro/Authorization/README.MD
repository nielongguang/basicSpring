#使用shiro通常的过程

 shiro是从根对象开始导航，Shiro 支持的依赖注入：public
                 空参构造器对象的创建、setter依赖注入。
*   Authenticator    
>加载验证登录模块  
  ModularRealmAuthenticator  
  此处可以配置登录策略authenticationStrategy
  
*  authorizer
>加载授权管理模块

* realm
>此处为realm域模块，决定该realm是什么类型，配置好域，然后放入SecurityManager加载，通常在此处已经把SecurityUtils.setSecurityManager(securityManager)写入，方便全局使用

##ini文件配置指南

**[main]**
#####提供了对根对象securityManager及其依赖的配置
>securityManager=org.apache.shiro.mgt.DefaultSecurityManager
…………
securityManager.realms=$jdbcRealm


**[users]**
#####提供了对用户/密码及其角色的配置，用户名=密码，角色1，角色2
>username=password,role1,role2  


**[roles]**
#####提供了角色及权限之间关系的配置，角色=权限1，权限2
>role1=permission1,permission2


**[urls]**
#####用于web，提供了对web url拦截相关的配置，url=拦截器[参数]，拦截器
>/index.html = anon
/admin/** = authc, roles[admin], perms["permission1"]


 
                 
