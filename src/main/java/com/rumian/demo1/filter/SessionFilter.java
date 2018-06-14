package com.rumian.demo1.filter;


import com.rumian.demo1.model.Admin;
import com.rumian.demo1.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "sessionFilter",urlPatterns = "/test/*")
public class SessionFilter implements Filter {
    @Autowired
    IAdminService iAdminService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        Admin currentAdmin = iAdminService.getAdmin();
        if (currentAdmin != null){
            //存在用户刷新redis用户过期时间
            iAdminService.updateUserTime();
        }else {
            //iAdminService.putUserToRedis(currentAdmin);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
