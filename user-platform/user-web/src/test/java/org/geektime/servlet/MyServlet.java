package org.geektime.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Enumeration;

/**
 * servlet test
 *
 * @author ViJay
 * @date 2021/3/21 19:51
 */
public class MyServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String key = initParameterNames.nextElement();
            String value = config.getInitParameter(key);
        }
    }
}
