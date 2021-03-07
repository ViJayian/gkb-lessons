package org.geektimes.projects.user.sql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * jndi方式
 *
 * @author ViJay
 * @date 2021/3/3 22:36
 */
public class JNDIManager extends DBConnectionManager {
    @Override
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
            e.printStackTrace();
        }
        return connection;
    }
}
