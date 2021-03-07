package org.geektimes.projects.user.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;

// web.xml中注册listener
//@WebListener
public class DBConnectionInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        Connection connection = getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            // Obtain our environment naming context
            Context envCtx = (Context) context.lookup("java:comp/env");
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
    }
}
