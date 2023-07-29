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

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取提交过来的用户id
        String uid=req.getParameter("uid");
        UserService service=new UserServiceImpl();
        //通过id查询用户的信息
        User user=service.findUserById(uid);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }
}
