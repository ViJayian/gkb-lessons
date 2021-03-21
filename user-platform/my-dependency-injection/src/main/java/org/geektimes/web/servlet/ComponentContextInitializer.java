package org.geektimes.web.servlet;

import org.geektimes.web.filter.MyFilter;
import org.geektimes.web.listener.ComponentInitializerListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * componet init
 *
 * @author ViJay
 * @date 2021/3/21 16:03
 */
public class ComponentContextInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.addListener(ComponentInitializerListener.class);
        ClassLoader classLoader = servletContext.getClassLoader();
        System.out.println(classLoader);
    }
}
