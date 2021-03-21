package org.geektimes.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filter
 *
 * @author ViJay
 * @date 2021/3/21 20:14
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("myfilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String servletPath = httpRequest.getServletPath();
            String pathInfo = httpRequest.getPathInfo();
            String requestURI = httpRequest.getRequestURI();
            System.out.println(requestURI);
            System.out.println(contextPath + servletPath + pathInfo);
        }
        chain.doFilter(request, response);
    }




    @Override
    public void destroy() {

    }
}
