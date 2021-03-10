package org.geektimes.web.mvc.context;

import org.geektimes.web.mvc.controller.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.naming.*;
import javax.servlet.ServletContext;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * 组件上下文
 * 通过注入ServletContext,将JNDI的配置注册成全局的组件
 *
 * @author ViJay
 * @date 2021/3/9 0:36
 */
public class ComponentContext {

    private static ServletContext servletContext;

    public static final String COMPONENT_NAME = ComponentContext.class.getName();

    private static final String JDNI_CONTEXT_NAME = "java:comp/env";

    private static final Logger logger = Logger.getLogger(COMPONENT_NAME);

    private ClassLoader classLoader;

    private Context context;

    private Map<String, Object> componentsMap = new LinkedHashMap<>();

    /**
     * 初始化
     * 1.获取 ServletContext
     * 2.当前组件上下文放入容器
     * 3.获取ClassLoader
     * 4.初始化JNDI {@link javax.naming.InitialContext} 环境上下文实例
     * 5.所有组件加载到内存 put map
     * 6.组件初始化
     *
     * @param servletContext
     */
    public void init(ServletContext servletContext) {
        ComponentContext.servletContext = servletContext;
        servletContext.setAttribute(COMPONENT_NAME, this);
        // 获取当前ServletContext(webapp) ClassLoader
        this.classLoader = servletContext.getClassLoader();
        // 初始化JNDI环境实例
        initJDNIEnvContext();
        // 加载 context.xml中配置的对象到内存中
        instantiateComponents();
        // 初始化组件
        initializeComponents();
    }

    /**
     * 初始化组件（支持 Java 标准 Commons Annotation 生命周期）
     * <ol>
     *  <li>注入阶段 - {@link Resource}</li>
     *  <li>初始阶段 - {@link PostConstruct}</li>
     *  <li>销毁阶段 - {@link PreDestroy}</li>
     * </ol>
     */
    private void initializeComponents() {
        componentsMap.values().forEach(component -> {
            Class<?> componentClass = component.getClass();
            // 注入
            injectComponents(component, componentClass);
            // 初始化
            processPostConstruct(component, componentClass);
            // todo 销毁
            processPreDestroy();
        });
    }

    /**
     * 注入，为标注 {@link Resource} 的字段赋值
     *
     * @param component
     * @param componentClass
     */
    private void injectComponents(Object component, Class<?> componentClass) {
        //todo
        Stream.of(componentClass.getDeclaredFields())
                .filter(field -> {
                    //非static和标注了Resource的字段
                    int mods = field.getModifiers();
                    return !Modifier.isStatic(mods) &&
                            field.isAnnotationPresent(Resource.class);
                }).forEach(field -> {
            Resource resource = field.getAnnotation(Resource.class);
            String resourceName = resource.name();
            Object injectedObject = lookupComponent(resourceName);
            field.setAccessible(true);
            try {
                // 注入目标对象
                field.set(component, injectedObject);
            } catch (IllegalAccessException e) {
            }
        });
    }

    /**
     * 初始化 {@link PostConstruct}
     *
     * @param component
     * @param componentClass
     */
    private void processPostConstruct(Object component, Class<?> componentClass) {
        Stream.of(componentClass.getMethods())
                .filter(method ->
                        !Modifier.isStatic(method.getModifiers()) &&      // 非 static
                                method.getParameterCount() == 0 &&        // 没有参数
                                method.isAnnotationPresent(PostConstruct.class) // 标注 @PostConstruct
                ).forEach(method -> {
            // 执行目标方法
            try {
                method.invoke(component);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void processPreDestroy() {
    }

    /**
     * 实例化组件
     */
    private void instantiateComponents() {
        //获取所有组件的名称
        List<String> componentNames = listAllComponentNames();
        // 依赖查找，实例化对象（Tomcat BeanFactory setter方法）
        componentNames.forEach(name -> componentsMap.put(name, lookupComponent(name)));
    }

    /**
     * 组件查找
     *
     * @param name
     * @return
     */
    private <C> C lookupComponent(String name) {
        // todo
        try {
            return (C) this.context.lookup(name);
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new NoSuchElementException(name);
        }
    }

    /**
     * 外部调用 ，依赖查找
     *
     * @param name
     * @param <C>
     * @return
     */
    public <C> C getBean(String name) {
        return (C) this.componentsMap.get(name);
    }

    /**
     * 获取所有controllerBeans
     *
     * @return
     */
    public List<Controller> getAllControllerBeans() {
        List<Controller> list = new ArrayList<>();
        Collection<Object> values = this.componentsMap.values();
        for (Object value : values) {
            if (value instanceof Controller) {
                Controller controller = (Controller) value;
                list.add(controller);
            }
        }
        return list;
    }

    private List<String> listAllComponentNames() {
        return listComponentNames("/");
    }

    private List<String> listComponentNames(String name) {
        //todo
        try {
            NamingEnumeration<NameClassPair> e = this.context.list(name);
            // 当前JNDI节点下没有数据
            if (Objects.isNull(e)) {
                return Collections.emptyList();
            }

            List<String> fullNames = new LinkedList<>();
            while (e.hasMoreElements()) {
                NameClassPair element = e.nextElement();
                String className = element.getClassName();
                Class<?> targetClass = this.classLoader.loadClass(className);
                //todo 如果当前名称是目录（Context 实现类）的话，递归查找
                if (Context.class.isAssignableFrom(targetClass)) {
                    fullNames.addAll(listComponentNames(element.getName()));
                } else {
                    // 否则，当前名称绑定目标类型的话话，添加该名称到集合中
                    String fullName = name.startsWith("/") ?
                            element.getName() : name + "/" + element.getName();
                    fullNames.add(fullName);
                }
            }
            return fullNames;
        } catch (NamingException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化JNDI {@link javax.naming.InitialContext}
     * https://tomcat.apache.org/tomcat-8.5-doc/jndi-resources-howto.html
     */
    private void initJDNIEnvContext() {
        //todo
        try {
            Context initCtx = new InitialContext();
            this.context = (Context) initCtx.lookup(JDNI_CONTEXT_NAME);
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * 从ServletContext容器中，获取ComponentContext实例
     *
     * @return
     */
    public static ComponentContext getInstance() {
        return (ComponentContext) servletContext.getAttribute(COMPONENT_NAME);
    }

    /**
     * destory方法
     */
    public void destory() {

    }
}
