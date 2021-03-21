package org.geektimes.projects.user.sql;

import org.geektimes.web.context.ComponentContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * jndi方式
 *
 * @author ViJay
 * @date 2021/3/3 22:36
 */
public class JNDIManager {

    private static final Logger logger = Logger.getLogger(JNDIManager.class.getName());

    /**
     * 通过ComponentContext组件上下文获取数据连接
     * @return
     */
    public Connection getConnection() {
        ComponentContext instance = ComponentContext.getInstance();
        DataSource dataSource = instance.getBean("jdbc/UserPlatformDB");
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                System.out.println("获取数据库连接成功！");
            }
            return connection;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("");
        }
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
            e.printStackTrace();
        }
        return connection;
    }*/
}
