[TOC]

# gkb-java

# 1.环境

## 0.软件环境

SqluirreL Sql client

## 1.应用容器 

Servlet  Engine -Tomcat8

## 2.Web服务

基于Servlet实现Mvc框架，支持JAX-RS注解

GoF23 Patterns

J2EE Core Patterns 

## 3.数据库

JDBC

### 1.Derby数据库使用

- 首次创建derby数据库

```
connect 'jdbc:derby:/db/user-platform;create=true';
```

- 连接derby数据库

```
PS D:\> ij
ij 版本 10.13
ij> connect 'jdbc:derby:/db/user-platform;';
```

# 2.Servlet

CGI-基于进程，PHP，Apache Httpd

Servlet-基于线程（一个线程处理一个请求）

- Struts 1,2

- Spring WebMVC
- JSF

### servlet重要的点

- api使用
- 架构设计

### java归档文件

- zip
  - jar
  - war
  - ear

ZipFile JarFile

### Servlet组件注册方式

- 传统web.xml

- 注解（Servlet3.0）@WebServlet
- 编码注册（Servlet3.0）ServletContext

### Servlet生命周期

- 声明（应用）
- （容器）

### Filter生命周期



### Jsp

jsp就是个servlet的扩展

通过模板代码-> Servlet字节码



### Servlet 处理静态内容

Tomcat: DefaultServlet

Jetty:

Weblogic

WebSphere

问题：SpringBoot将css和js放在static目录下，可以被读取

DefaultServletRequestHandler

### Servlet Forward技术

- 实例一：SpringFramework Web DefaultServletHttpRequsetHandler forward到容器servlet实例
- 实例二：自研Web Mvc框架 
- 实例三：Spring WebMvc模板渲染



Servlet Forward 是转发请求到下一个Servlet

Servlet Forward 是否会转发到Filter



### 自研WebMvc框架

控制器接口：Controller

- 页面渲染Controller:PageController(继承Controller)
- RestBodyController:RestController(继承Controller)



todolist

- servlet流程图

- servlet规范（servlet3.1 final）

  - http paramter(http://www.abc.com/def?a=1&a=2&a=3) 
    - gerparamter 返回第一个
    - getparamtervalues 返回多个
  - non blocking io
    - Tomcat Nio 模型 Apache Tomcat
  - Response

  

# 3.JNDI

Java Naming Directory Interface

java命名和目录接口

- JNDI获取数据源
- JNDI注入对象



# 4.jpa

## 1.Java Persistence Api (JPA) 规范

1. 实体使用@Entity注解
2. 必须包含默认构造器（需要 class.newInstance），public或者protected
3. 实体类禁止是final
   - 延迟加载是通过字节码提升做的，Session获取到的Entity Object类型是一个字节码提升的类，原始的类型是它的父类，因此这个类不能是final的。
4. 不能是枚举或者接口



# 5.配置管理和Java Logging

## 1.整合MicroProfile Config

- Maven坐标

```
<!-- MicroProfile -->
<dependency>
<groupId>org.eclipse.microprofile.config</groupId>
<artifactId>microprofile-config-api</artifactId>
<version>${microprofile-config-api.version}</version>
</dependency>
```

## 2.引入项目

### 1.配置统一门面**org.eclipse.microprofile.config.Config**

- 作用： 通过ServiceLoader加载ConfigSource
- Config比较ConfigSource，增加了类型转换

### 2.配置来源org.eclipse.microprofile.config.spi.ConfigSource

### 3.配置**org.eclipse.microprofile.config.spi.ConfigProviderResolver**

## 4.Java Logging

打印日志，Filter，LogRecord,判断级别，国际化文案

# 6.监控管理



# 作业

1. 第一周

- [x] 通过自研 Web MVC 框架实现（可以自己实现）一个用户注册，forward 到一个成功的页面（JSP 用法）/register

- [x] 通过 Controller -> Service -> Repository 实现（数据库实现）

- [x] （非必须）JNDI 的方式获取数据库源（DataSource），在获取 Connection

2. 第二周

- [x] 通过课堂上的简易版依赖注入和依赖查找，实现用户注册功能
- [x] 通过 UserService 实现用户注册注册用户需要校验
- [x] Id：必须大于 0 的整数
- [x] 密码：6-32 位 电话号码: 采用中国大陆方式（11 位校验）

3. 第三周

- 整合 https://jolokia.org/

- [ ] 实现一个自定义 JMX MBean，通过 Jolokia 做Servlet 代理

- 继续完成 Microprofile config API 中的实现扩展

- [x] org.eclipse.microprofile.config.spi.ConfigSource实现，包括 OS 环境变量，以及本地配置文件
- [x] 扩展 org.eclipse.microprofile.config.spi.Converter实现，提供 String 类型到简单类型
- [x] 通过 org.eclipse.microprofile.config.Config 读取当前应用名称,应用名称 property name = "application.name"







