package org.geektimes.test;

import org.eclipse.microprofile.config.Config;
import org.geektimes.jmx.JMXDemo;
import org.geektimes.projects.user.domain.User;
import org.geektimes.web.context.ComponentContext;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

/**
 * 测试
 *
 * @author ViJay
 * @date 2021/3/14 10:51
 */
public class TestListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(TestListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        testPropertyFromServletContext(servletContext);
        testJNDIPropertyValue(servletContext);
        // test MicroProfile init Properties
//        testMicroProfileProperties("application.name");

        //test jolokia mbean
        //http://localhost:8081/jolokia/read/org.geektimes.jmx:type=User
        testJolokiaMbean();
    }

    private void testJolokiaMbean() {
        try {
            //获取平台MBean Server
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            //命名
            ObjectName objectName = new ObjectName("org.geektimes.jmx:type=User");

            User user = new User();
            server.registerMBean(JMXDemo.createUserMBean(user), objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置
     */
    private void testMicroProfileProperties(String property) {
        ComponentContext instance = ComponentContext.getInstance();
        Config config = instance.getBean("bean/JavaConfig");
        String name = config.getValue(property, String.class);
        System.out.println("application name = [" + name + "]"); // application name = [application-user-web]
    }

    /**
     * 测试获取JNDI Property value
     *
     * @param servletContext
     */
    private void testJNDIPropertyValue(ServletContext servletContext) {
        ComponentContext componentContext = ComponentContext.getInstance();
        Object value = componentContext.lookupComponent("maxValue");
        logger.info("JNDI Value = [" + value + "]");
    }

    /**
     * 测试获取web.xml org.geektimes.web.context-param
     *
     * @param servletContext
     */
    private void testPropertyFromServletContext(ServletContext servletContext) {
        String applicationName = servletContext.getInitParameter("application.name");
        logger.info("applicationName = [" + applicationName + "]");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
