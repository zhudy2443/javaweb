package com.example.mybook.web.servlet;

import com.example.mybook.service.Impl.UserServiceImpl;
import com.example.mybook.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelSelectedUserServlet")
public class DelSelectedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取表单提交过来的要删除的用户的id
        String[] ids=req.getParameterValues("chkUser");
        UserService service=new UserServiceImpl();
        service.DelSelectedUser(ids);
        resp.sendRedirect(req.getContextPath()+"/UserListServlet");
    }
}
