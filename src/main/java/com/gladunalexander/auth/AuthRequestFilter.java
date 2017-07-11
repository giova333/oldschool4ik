package com.gladunalexander.auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alex on 10.07.2017.
 */

@WebFilter(urlPatterns = "/*", filterName = "AuthRequestFilter")
public class AuthRequestFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String loginURI = httpServletRequest.getContextPath() + "/login";

        HttpSession session = httpServletRequest.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest){
            filterChain.doFilter(new AuthRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        }else {
            httpServletResponse.sendRedirect(loginURI);
        }
    }

    public void destroy() {

    }
}
