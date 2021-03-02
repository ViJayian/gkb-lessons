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

  

