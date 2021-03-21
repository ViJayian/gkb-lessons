package org.geektimes.projects.user.web.listener;

import org.geektimes.web.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * component
 *
 * @author ViJay
 * @date 2021/3/9 23:30
 */
public class ComponentInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    /**
     * 容器启动，触发监听，调用 {@link org.geektimes.web.context.ComponentContext} 初始化
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        ComponentContext componentContext = new ComponentContext();
        //上下文加载
        componentContext.init(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ComponentContext context = ComponentContext.getInstance();
        context.destory();
    }
}
