package org.geektimes.configuration.microprofile.config.servlet;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.geektimes.utils.ThreadConfigHolder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * ServletContextConfigInitializer
 *
 * @author ViJay
 * @date 2021/3/21 14:15
 */
public class ServletContextConfigInitializer implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ServletContextConfigInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) throws UnsupportedOperationException {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletContextConfigSource.setServletContext(servletContext);
        ServletContextConfigSource servletContextConfigSource = new ServletContextConfigSource();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ConfigProviderResolver resolver = ConfigProviderResolver.instance();
        ConfigBuilder builder = resolver.getBuilder();
        builder.forClassLoader(classLoader);
        builder.forClassLoader(classLoader)
                .addDefaultSources()
                .addDiscoveredSources()
                .addDiscoveredConverters()
                .withSources(servletContextConfigSource);
        Config config = builder.build();
        resolver.registerConfig(config, classLoader);

        testConfigProperties(config);

        addServletContextAttribute(servletContext, config);
        addThreadLocalVariables(config);
    }

    /**
     * 设置到ThreadLocal
     * @param config
     */
    private void addThreadLocalVariables(Config config) {
        ThreadConfigHolder.setConfig(config);
        logger.info("==========> 添加变量到Thread Local ==========");
    }

    /**
     * 添加config到servletContex中
     * 在 my-web-mvc MyRestController中尝试获取
     */
    private void addServletContextAttribute(ServletContext servletContext, Config config) {
        servletContext.setAttribute("config", config);
    }

    private void testConfigProperties(Config config) {
        String applicationName = config.getValue("application.name", String.class);
        Integer port = config.getValue("server.port", Integer.class);
        logger.info(String.format("====== my-configuration module get application name is [%s], port = [%s] ====== \n", applicationName, port));
        System.out.printf("====== my-configuration module get application name is [%s], port = [%s] ====== \n", applicationName, port);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("==========> contextDestroyed ==========");
        ThreadConfigHolder.release();
        logger.info("==========> 释放 Thread Local 变量 ==========");
    }
}
