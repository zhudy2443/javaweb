package com.example.mybook.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String uri=request.getRequestURI();
        //获取当前请求的uri
        if(uri.contains("/login.jsp")||uri.contains("/LoginServlet")||uri.contains("/CheckCodeServlet")||uri.contains("/css/")||uri.contains("/fonts/")||uri.contains("/js/")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //判断当前有没有登录
            HttpSession session= request.getSession();
            if(session.getAttribute("user")!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                request.setAttribute("error_msg","没有登录！");
                request.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
