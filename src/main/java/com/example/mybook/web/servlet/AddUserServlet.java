package com.example.mybook.web.servlet;

import com.example.mybook.pojo.User;
import com.example.mybook.service.Impl.UserServiceImpl;
import com.example.mybook.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //1.获取表单提交过来的数据
        Map<String,String[]> map=req.getParameterMap();//一次性获取表单提交过来的数据封装到map集合
        User user=new User();
        //将map里的数据封装到user对象中
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service=new UserServiceImpl();
        service.add(user);
        resp.sendRedirect(req.getContextPath()+"/UserListServlet");

    }
}
