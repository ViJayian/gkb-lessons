package org.geektimes.configuration.microprofile.config.servlet;

import org.geektimes.configuration.microprofile.config.source.AbstractMapConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Map;

/**
 * servlet properties
 *
 * @author ViJay
 * @date 2021/3/21 14:17
 */
public class ServletContextConfigSource extends AbstractMapConfigSource {

    private static ServletContext servletContext;

    public static void setServletContext(ServletContext servletContext) {
        ServletContextConfigSource.servletContext = servletContext;
    }

    protected ServletContextConfigSource() {
        super("servlet init paramter", 1000);
    }

    @Override
    protected void prepareConfigData(Map map) throws Throwable {
        Enumeration<String> paramterNames = servletContext.getInitParameterNames();
        while (paramterNames.hasMoreElements()) {
            String key = paramterNames.nextElement();
            map.put(key, servletContext.getInitParameter(key));
        }
    }
}
