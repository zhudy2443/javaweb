package com.example.mybook.service.Impl;

import com.example.mybook.dao.Impl.UserDaoImpl;
import com.example.mybook.dao.UserDao;
import com.example.mybook.pojo.PageBean;
import com.example.mybook.pojo.User;
import com.example.mybook.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public User findUserByNameAndPwd(String userName, String password) {
        return userDao.findUserByNameAndPwd(userName,password);
    }

    @Override
    public PageBean<User> findUser(String _currentPage,String _rows,Map<String, String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
        //从哪条记录开始查询
        int start=(currentPage-1)*rows;
        List<User> list=userDao.findUser(start,rows,condition);
        PageBean<User> pb=new PageBean<User>();
        pb.setList(list);
        pb.setCurrentPage(currentPage);
        //总共有多少条记录
        int recordCount=userDao.findUserCount(condition);
        pb.setRecordCount(recordCount);
        //总共有多少页
        int pageCount=recordCount%rows==0?recordCount/rows:recordCount/rows+1;
        pb.setPageCount(pageCount);
        return pb;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delUser(String _id) {
        int id=Integer.parseInt(_id);
        userDao.delUser(id);
    }

    @Override
    public void DelSelectedUser(String[] ids) {
        for (String id : ids) {
            userDao.delUser(Integer.parseInt(id));
        }
    }

    @Override
    public User findUserById(String uid) {
        return userDao.findUserById(uid);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
