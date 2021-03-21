package org.geektimes.projects.user.web.listener;

import org.geektimes.web.context.ComponentContext;
import org.geektimes.projects.user.sql.JNDIManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

/**
 * web.xml中注册listener
 * 配置顺序 listener> servlet > filter
 * 实现ServletContextListener在容器启动或者关闭做一些操作
 */
//@WebListener
public class DBConnectionInitializerListener implements ServletContextListener {

    private static final String DB_RESOURCE_NAME = "bean/JNDIManager";

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
//        Connection connection = getConnection();
        ComponentContext instance = ComponentContext.getInstance();
        JNDIManager jndiManager = instance.getBean(DB_RESOURCE_NAME);
        Connection connection = jndiManager.getConnection();
        if (connection != null) {
            System.out.println("获取数据库连接成功");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /*public Connection getConnection() {
        Connection connection = null;
        try {
            Context org.geektimes.web.context = new InitialContext();
            // Obtain our environment naming org.geektimes.web.context
            Context envCtx = (Context) org.geektimes.web.context.lookup("java:comp/env");
            // Look up our data source
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/UserPlatformDB");
            connection = dataSource.getConnection();
        } catch (Throwable e) {
            servletContext.log(e.getMessage(), e);
        }

        if (connection != null) {
            servletContext.log("获取数据库连接成功");
        }
        return connection;
    }*/
}
