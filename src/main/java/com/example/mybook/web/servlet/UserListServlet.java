package com.example.mybook.web.servlet;

import com.example.mybook.pojo.PageBean;
import com.example.mybook.pojo.User;
import com.example.mybook.service.Impl.UserServiceImpl;
import com.example.mybook.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取当前第几页
        String currentPage=req.getParameter("currentPage");
        //获取每页显示多少条数据
        String rows=req.getParameter("rows");
        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="3";
        }

        //1.获取查询条件
        req.setCharacterEncoding("utf-8");
        //map集合的数据
        //键对应的表单元素的name:name,address:email  值对应的是我们填写的值
        Map<String,String[]>  condition = req.getParameterMap();//一次性获取表单传过来的所有数据
        //2.根据查询条件调用业务逻辑层查询相应的数据
        UserService service=new UserServiceImpl();
        PageBean<User> pb=service.findUser(currentPage,rows,condition);
        //3.将返回的数据列表传到list.jsp页面显示出来
        req.setAttribute("pb",pb);
        req.setAttribute("condition",condition);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
