package com.mobicare.poc.filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

public class MobicareSwaggerFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/mobicare-swagger-resources");
        dispatcher.forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
