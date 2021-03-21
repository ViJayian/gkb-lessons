package org.geektimes.configuration.microprofile.config.servlet;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextConfigInitializer
 *
 * @author ViJay
 * @date 2021/3/21 14:15
 */
public class ServletContextConfigInitializer implements ServletContextListener {
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
    }

    private void testConfigProperties(Config config) {
        String applicationName = config.getValue("application.name", String.class);
        Integer port = config.getValue("server.port", Integer.class);
        System.out.printf("application name is [%s], port = [%s]", applicationName, port);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
