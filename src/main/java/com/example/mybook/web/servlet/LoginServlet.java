package com.example.mybook.web.servlet;

import com.example.mybook.pojo.User;
import com.example.mybook.service.Impl.UserServiceImpl;
import com.example.mybook.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取验证码
        String verifycode= req.getParameter("verifycode");
        String code=(String)req.getSession().getAttribute("CHECKCODE_SERVER");
        //equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写。
        if(verifycode.equalsIgnoreCase(code)){
            //验证用户名和密码
            UserService service=new UserServiceImpl();
            String userName=req.getParameter("userName");//用户名
            String password=req.getParameter("password");//密码
            //调用业务逻辑层的方法
            User user=service.findUserByNameAndPwd(userName,password);
            //登陆成功后
            if(user!=null){
                //记录session(用它来判断访问其他资源的时候有没有登录)
                req.getSession().setAttribute("user",user);
                resp.sendRedirect(req.getContextPath()+"/UserListServlet");
            }else{
                //登录没有成功
                req.setAttribute("error_msg","用户名或密码错误！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }else{
            //验证码错误
            req.setAttribute("error_msg","验证码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
