package org.geektimes.configuration.microprofile.config.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * servlet config init
 *
 * @author ViJay
 * @date 2021/3/21 14:14
 */
public class ServletConfigInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.addListener(ServletContextConfigInitializer.class);
    }
}
