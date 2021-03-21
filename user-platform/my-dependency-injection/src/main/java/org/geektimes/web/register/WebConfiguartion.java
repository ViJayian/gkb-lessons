package org.geektimes.web.register;

import org.geektimes.web.filter.RegisterFilter;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * web config 通过spi方式加载WebConfiguration，并且通过java编码方式添加Filter
 *
 * @author ViJay
 * @date 2021/3/21 20:37
 */
public class WebConfiguartion implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic registerFilter = servletContext.addFilter("RegisterFilter", RegisterFilter.class);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        registerFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
