package com.example.mybook.dao;

import com.example.mybook.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User findUserByNameAndPwd(String userName, String password);

    List<User> findUser(Integer start,Integer rows,Map<String, String[]> condition);

    int findUserCount(Map<String, String[]> condition);

    void add(User user);
    void delUser(int id);

    User findUserById(String uid);

    void updateUser(User user);
}
