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

## 1.Servlet是什么

Servlet是基于Java的Web组件，容器托管的，用于生成动态内容。Servlet最终被编译成与平台无关的字节码文件，可以被基于java的web server加载

## 2.Servlet容器是什么

Servlet容器是web server或者application server的一部分，提供基于请求/响应发送模型的网络服务，解码基于MIME的请求，并且格式化基于MIME的响应，

Servlet容器也包含了Servlet的生命周期。

## 3.事件序列

1. web浏览器发送请求到web服务器
2. web服务器接受请求并且交给servlet容器
3. servlet根据配置选择相应的servlet进行处理
4. 产生响应内容返回给客户端

## 4.Servlet接口

### 1.Servlet

Servlet是Java Servlet Api的核心抽象，所有Servlet类都必须直接或者间接实现Servlet接口，通常继承HttpServlet实现自己的Servlet即可。

```java
javax.servlet.Servlet
public interface Servlet {
    
}

javax.servlet.GenericServlet
public abstract class GenericServlet 
    implements Servlet, ServletConfig, java.io.Serializable
{}

javax.servlet.http.HttpServlet
public abstract class HttpServlet extends GenericServlet
{}   
```

### 2.请求处理方法

javax.servlet.Servlet#service

当请求到达的时候，由该方法路由到一个Servlet的实例

### 3.Servlet默认线程不安全

Web 应用程序的并发请求处理通常需要 Web 开发人员去设计适合多线程执行的 Servlet，从而保证 service

方法能在一个特定时间点处理多线程并发执行。（注：即 Servlet 默认是线程不安全的，需要开发人员处理

多线程问题），（通常 Web 容器对于并发请求将使用同一个 servlet 处理，并且在不同的线程中并发执行 service 方法。）

### 4.解决方式：

总结1：servlet是线程不安全的，而造成这种原因主要是以为**实例变量不正确的使用**。
总结2 ：尽量不使用synchronized来解决Servlet线程安全问题

### 5.SingleThreadModel

SingleThreadModel 接口的作用是保证一个特定servlet 实例的service 方法在一个时刻仅能被一个线程执行，

一定要注意，此保证仅适用于每一个 servlet 实例，因此容器可以选择池化这些对象。有些对象可以在同一

时刻被多个 servlet 实例访问

## 5.Servlet生命周期

### 1.加载和实例化

Servlet容器负责加载和实例化Servlet

### 2.初始化

Servlet被实例化完毕，容器接下来必须在处理客户端请求之前初始化该Servlet实例。通过Servlet实例的init方法初始化，提供一个唯一参数ServletConfig

```java
javax.servlet.Servlet#init
    public void init(ServletConfig config) throws ServletException;
```

ServletConfig可以获取Web应用配置的参数，也可以访问ServletContext对象，ServletContext描述了Servlet运行环境

### 3.请求处理

Servlet完成初始化后，可以处理请求了，请求由ServletRequest对象表示，响应由ServletResponse对象表示，http请求一般对应HttpServletRequest和HttpServletResponse

```java
javax.servlet.ServletRequest
    javax.servlet.http.HttpServletRequest
    
javax.servlet.ServletResponse
    javax.servlet.http.HttpServletResponse
```

### 4.终止服务

移除Servlet时，调用Servlet的destory方法

## 6.Request对象的生命周期

每个request对象只在servlet的service方法的作用域内，或过滤器doFilter方法的作用域有效，除非是启动了异步处理startAsync

## 7.ServletContext

Servlet上下文

### 1.配置方法

3.0开始添加到ServletContext，启用编程的方式定义Servlet，Filter和他们映射的url，这些方法只能从**javax.servlet.ServletContextListener#contextInitialized**方法或者**javax.servlet.ServletContainerInitializer#onStartup**初始化过程进行调用

### 2.编程式配置Servlet，Filter，Listener

### 3.上下文属性

xxxAttribute

### 4.资源 

javax.servlet.ServletContext#getResource

javax.servlet.ServletContext#getResourceAsStream

需要一个以 '/'开头的字符串为参数

## 8.Response

生命周期：

在servlet的service方法的范围内或在filter的doFilter方法范围内都是有效的

## 9.Filter过滤器





## 课程笔记

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

https://gitee.com/vijayian/microprofile-config

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

## 3.MicroProfile核心类

### 1.org.eclipse.microprofile.config.spi.ConfigBuilder

```java
//A builder for manually creating a configuration instance
//手动创建配置实例的构建器
public interface ConfigBuilder {
    //添加默认的配置资源 
    ConfigBuilder addDefaultSources();

    // 所有可以被 forClassLoader(ClassLoader) 方法发现的配置资源
    ConfigBuilder addDiscoveredSources();

    // 所有可以被 forClassLoader(ClassLoader) 方法发现的converters
    ConfigBuilder addDiscoveredConverters();

    //指定构建此配置的classLoader
    ConfigBuilder forClassLoader(ClassLoader loader);

    //添加特定的ConfigSource到配置
    ConfigBuilder withSources(ConfigSource... sources);

    //添加converter
    ConfigBuilder withConverters(Converter<?>... converters);

     //添加converter
    <T> ConfigBuilder withConverter(Class<T> type, int priority, Converter<T> converter);

	//创建新的Config实例
    Config build();
}
```

### 2.org.eclipse.microprofile.config.spi.ConfigProviderResolver

```java
//MicroProfile配置规范的服务提供者
public abstract class ConfigProviderResolver {
    /**
     * Construct a new instance.
     */
    protected ConfigProviderResolver() {
    }

    private static volatile ConfigProviderResolver instance = null;

    //org.eclipse.microprofile.config.ConfigProvider#getConfig() 通过此方式获取配置（是ConfigProvider类）
    public abstract Config getConfig();

    // 同上
    public abstract Config getConfig(ClassLoader loader);

   	//创建一个当前线程的ConfigBuilder实例 （官方标注高级方法-手工创建的实例）
    public abstract ConfigBuilder getBuilder();

    // 注册指定的Config实例，如果给定的classLoader是null，则使用当前线程的classLoader（官方标注高级方法-手工创建的实例）
    public abstract void registerConfig(Config config, ClassLoader classLoader);

   	//在应用销毁的时候，Config配置也会正常释放，如果想提早释放，实现这个方法
    public abstract void releaseConfig(Config config);

    //获取解析器实例，如果不为null，或者手动指定，则返回，否则，根据ServiceLoader定位第一个classLoader加载的实现
    public static ConfigProviderResolver instance() {
        if (instance == null) {
            synchronized (ConfigProviderResolver.class) {
                if (instance != null) {
                    return instance;
                }
                instance = loadSpi(ConfigProviderResolver.class.getClassLoader());
            }
        }

        return instance;
    }

    private static ConfigProviderResolver loadSpi(ClassLoader cl) {
        ServiceLoader<ConfigProviderResolver> sl = ServiceLoader.load(
                ConfigProviderResolver.class, cl);
        final Iterator<ConfigProviderResolver> iterator = sl.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IllegalStateException(
                "No ConfigProviderResolver implementation found!");
    }

    //设置实例。它被不支持的OSGi环境使用
    public static void setInstance(ConfigProviderResolver resolver) {
        instance = resolver;
    }
}
```

### 3.org.eclipse.microprofile.config.spi.ConfigSource

```java
//特定环境的配置，如支持JNDI命名服务的，属性文件的，数据库表的
public interface ConfigSource {
    /**
     * The name of the configuration ordinal property, "{@code config_ordinal}".
     */
    String CONFIG_ORDINAL = "config_ordinal";

    /**
     * The default configuration ordinal value, {@code 100}.
     */
    int DEFAULT_ORDINAL = 100;

    //获取配置
    default Map<String, String> getProperties() {
        Map<String, String> props = new HashMap<>();
        getPropertyNames().forEach((prop) -> props.put(prop, getValue(prop)));
        return props;
    }

    //获取所有属性名称
    Set<String> getPropertyNames();

   	//获取优先级
    default int getOrdinal() {
        String configOrdinal = getValue(CONFIG_ORDINAL);
        if (configOrdinal != null) {
            try {
                return Integer.parseInt(configOrdinal);
            } catch (NumberFormatException ignored) {

            }
        }
        return DEFAULT_ORDINAL;
    }
	// 获取value
    String getValue(String propertyName);

    //config source name
    String getName();
}
```

### 4.org.eclipse.microprofile.config.spi.ConfigSourceProvider

```java
//configuration source 的提供
//实现这个接口，可以提供0个或者多个ConfigSource为应用服务
/**Instances of this interface will be {@linkplain ConfigBuilder#addDiscoveredSources() discovered} via the
 * {@link java.util.ServiceLoader} mechanism and can be registered by providing a
 * {@code META-INF/services/org.eclipse.microprofile.config.spi.ConfigSourceProvider}
 * {@linkplain ClassLoader#getResource(String) resource} which contains the fully qualified class name of the custom
 * {@code ConfigSourceProvider} implementation.
 */
public interface ConfigSourceProvider {
    /**
     * Return the {@link ConfigSource} instances that are provided by this provider. The {@link java.lang.Iterable
     * Iterable} contains a fixed number of {@linkplain ConfigSource configuration sources}, determined at application
     * start time, and the config sources themselves may be static or dynamic. An empty {@link java.lang.Iterable
     * Iterable} may be returned if no sources are to be provided.
     *
     * @param forClassLoader
     *            the class loader which should be used for discovery and resource loading purposes
     * @return the {@link ConfigSource} instances to register to the configuration
     */
    Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader);
}
```

### 5.org.eclipse.microprofile.config.spi.Converter

```java
//一种将String转换为任意类型的机制
public interface Converter<T> extends Serializable {
    /**
     * Convert the given string value to a specified type. Callers <em>must not</em> pass in {@code null} for
     * {@code value}; doing so may result in a {@code NullPointerException} being thrown.
     *
     * @param value
     *            the string representation of a property value (must not be {@code null})
     * @return the converted value, or {@code null} if the value is empty
     * @throws IllegalArgumentException
     *             if the value cannot be converted to the specified type
     * @throws NullPointerException
     *             if the given value was {@code null}
     */
    T convert(String value) throws IllegalArgumentException, NullPointerException;
}
```

### 6.org.eclipse.microprofile.config.Config

```java
//解析被ConfigSource配置的属性，使用优先级高的，如果优先级值一样，ConfigSource#getName()使用排序
//访问config可以使用ConfigProvider
/*public void doSomething() {
    Config cfg = ConfigProvider.getConfig();
    String archiveUrl = cfg.getValue("my.project.archive.endpoint", String.class);
    Integer archivePort = cfg.getValue("my.project.archive.port", Integer.class);
}
*/
// See {@link #getValue(String, Class)} and {@link #getOptionalValue(String, Class)} for accessing a configured value.
public interface Config {
    /**
     * The value of the property specifies a single active profile.
     */
    String PROFILE = "mp.config.profile";

    /**
     * The value of the property determines whether the property expression is enabled or disabled. The value
     * <code>false</code> means the property expression is disabled, while <code>true</code> means enabled.
     *
     * By default, the value is set to <code>true</code>.
     */
    String PROPERTY_EXPRESSIONS_ENABLED = "mp.config.property.expressions.enabled";

    /*解析value为指定的类型
     * @return the resolved property value as an instance of the requested type (not {@code null})
     * @throws IllegalArgumentException 不能转换
     *             if the property cannot be converted to the specified type
     * @throws java.util.NoSuchElementException 属性不存在
     *             if the property is not defined or is defined as an empty string or the converter returns {@code null}
     */
    <T> T getValue(String propertyName, Class<T> propertyType);

    //获取ConfigValue
    ConfigValue getConfigValue(String propertyName);

    // getvalues
    default <T> List<T> getValues(String propertyName, Class<T> propertyType) {
        @SuppressWarnings("unchecked")
        Class<T[]> arrayType = (Class<T[]>) Array.newInstance(propertyType, 0).getClass();
        return Arrays.asList(getValue(propertyName, arrayType));
    }

    //同getValue
    <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType);

    
    default <T> Optional<List<T>> getOptionalValues(String propertyName, Class<T> propertyType) {
        @SuppressWarnings("unchecked")
        Class<T[]> arrayType = (Class<T[]>) Array.newInstance(propertyType, 0).getClass();
        return getOptionalValue(propertyName, arrayType).map(Arrays::asList);
    }

    //获取Configuration property names
    Iterable<String> getPropertyNames();

    //返回当前注册的ConfigSource
    Iterable<ConfigSource> getConfigSources();

    //获取指定类型的Converter
    <T> Optional<Converter<T>> getConverter(Class<T> forType);

    //返回特定类的实例
    <T> T unwrap(Class<T> type);
}
```

### 7.org.eclipse.microprofile.config.ConfigProvider

```java
//访问 Config 的核心类
//config提供了访问应用的配置，它可能被自动发现，或者手工创建或者注册
//默认的用法是使用 getConfig() 自动获得当前线程（classLoader）的配置
//一个配置configuration由 ConfigSource结合 Converter 组成，配置通过它们对应的ConfigSource#getOrdinal() ordinal value排序

//example:
//String restUrl = ConfigProvider.getConfig().getValue("myproject.some.remote.service.url", String.class);
//Integer port = ConfigProvider.getConfig().getValue("myproject.some.remote.service.port", Integer.class);
public final class ConfigProvider{
    private ConfigProvider() {
    }

    //获取当前应用，或者当前线程定义的 Config
    public static Config getConfig() {
        return ConfigProviderResolver.instance().getConfig();
    }

    //获取当前classLoader匹配的Config，如果没有这样的配置，将Config注册到给定的classLoader
    public static Config getConfig(ClassLoader cl) {
        return ConfigProviderResolver.instance().getConfig(cl);
    }
}
```

### 8.org.eclipse.microprofile.config.ConfigValue

```java
// 保存ConfigSource的配置属性名称，值，ConfigSource的名称
// 与Config一起使用 暴露configuration的元数据
public interface ConfigValue{
    /**
     * The name of the property.
     */
    String getName();

    /**
     * 获取转换后的value
     * @return the value of the property lookup or {@code null} if the property could not be found
     */
    String getValue();

    /**
     * 获取未转换的value
     * The value of the property lookup without any transformation (expanded , etc).
     * @return the raw value of the property lookup or {@code null} if the property could not be found.
     */
    String getRawValue();

    /**
     * ConfigSource name
     */
    String getSourceName();

    /**
     * ConfigSource 次序
     * @return the ConfigSource ordinal that loaded the property lookup or {@code 0} if the property could not be found
     */
    int getSourceOrdinal();
}
```



## 4.Java Logging

打印日志，Filter，LogRecord,判断级别，国际化文案

## 

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
- [x] 实现一个自定义 JMX MBean，通过 Jolokia 做Servlet 代理
- 继续完成 Microprofile config API 中的实现扩展
- [x] org.eclipse.microprofile.config.spi.ConfigSource实现，包括 OS 环境变量，以及本地配置文件
- [x] 扩展 org.eclipse.microprofile.config.spi.Converter实现，提供 String 类型到简单类型
- [x] 通过 org.eclipse.microprofile.config.Config 读取当前应用名称,应用名称 property name = "application.name"

4. 第四周

- 完善 my dependency-injection 模块

- [x] 脱离 web.xml 配置实现 ComponentContext 自动初始化
- [x] 使用独立模块并且能够在 user-web 中运行成功

- 完善 my-configuration 模块

- [x] Config 对象如何能被 my-web-mvc 使用
  - [x] 可能在 ServletContext 获取
  - [x] 如何通过 ThreadLocal 获取







