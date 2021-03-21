package org.geektimes.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 通过编码方式注册Filter
 *
 * @author ViJay
 * @date 2021/3/21 20:38
 */
public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("register Filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
