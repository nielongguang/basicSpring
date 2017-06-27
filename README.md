# basicSpring
 
## 基本结构
---com.cn.study.basicSpring   <br />
----config   <br />
------interceptor   <br />
----controller   <br />
----dao   <br />
----model   <br />
----dto   <br />
--resources  
----assets  
----views  

## 项目配置   <br />
```
1. 新建spring配置类  开启组件扫描 开启springmvc默认配置   实现WebMvcConfigurerAdapter  

  1)配置拦截器方法  
     新建拦截器类 实现拦截处理器(适配器) 根据需求重写方法 preHandle为请求处理前 postHandle为请求处理后  
     此配置可以用 直接将配置拦截器作为组件的方式替代 但是由于要添加 所以直接在此处配置拦截器,而不配置为组件  
  2)注册拦截器 重写 addInterceptors 方法,使用InterceptorRegistry的addInterceptors方法增加拦截器  
  3)空白转向注册 ViewControllerRegistry registry.addViewController("/使用路径").setViewName("/实际路径");  
  4)配置路径匹配 configurePathMatch 设置后缀路径取匹配 ,设置为false 则不会忽略小数点  
  5)配置上传的bean  MultipartResolver 配置最大容量为10MB   

2.配置viewResolver 内部资源视图解析器 配置前后缀,配置渲染方式(此方法可以不配置,默认为不配置)     
3.配置WebInitializer Web容器初始化 加载spring配置类 (其中配置了viewResolver )  
    新增servlet DispatcherServlet,设置映射为"/" 拦截所有路径  
    设置加载优先级为1  
4.新建日志模块 logback.xml  
```
此时项目基本的拦截/空白转向/路径匹配/文件上传等功能实现完成  
 
