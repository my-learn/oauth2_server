package com.dotnar.usc.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/oauth/authorize")
@Component
public class SimpleAuthenticationFilter implements Filter {

    public static ThreadLocal<String> TL = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String lang = servletRequest.getParameter("lang");
        TL.set(lang);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    public static String getLang() {
        return TL.get();
    }
}
