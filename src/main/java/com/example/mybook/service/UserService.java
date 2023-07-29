package com.example.mybook.service;

import com.example.mybook.pojo.PageBean;
import com.example.mybook.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findUserByNameAndPwd(String userName, String password);

    PageBean<User> findUser(String currentPage, String rows, Map<String, String[]> condition);

    void add(User user);

    void delUser(String id);

    void DelSelectedUser(String[] ids);

    User findUserById(String uid);

    void updateUser(User user);
}
