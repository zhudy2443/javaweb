package com.example.mybook.dao.Impl;

import com.example.mybook.dao.UserDao;
import com.example.mybook.pojo.User;
import com.example.mybook.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByNameAndPwd(String userName, String password) {
        try {
            return jdbcTemplate.queryForObject("select * from user where userName=? and password =?", new BeanPropertyRowMapper<User>(User.class), userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*
根据条件查询用户
 */
    @Override
    public List<User> findUser(Integer start,Integer rows,Map<String, String[]> condition) {
//        select * from user where name like "%aa%" and address like "%adf%" and email like "%asdf%"
        String sql = " select * from user where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        //获取所有的键的集合
        Set<String> keys = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key : keys) {
            if(key.equals("currentPage")||key.equals("rows")){
                continue;
            }
            if(key!=null&&!"".equals(key)) {
                builder.append(" and " + key + " like ? ");
                String value = condition.get(key)[0];//根据键得到值
                params.add("%" + value + "%");
            }
        }
        builder.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql=builder.toString();
        //System.out.println(sql);打印拼接的sql语句
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

    @Override
    public int findUserCount(Map<String, String[]> condition) {
        String sql = " select count(*) from user where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        //获取所有的键的集合
        Set<String> keys = condition.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key : keys) {
            if(key.equals("currentPage")||key.equals("rows")){
                continue;
            }
            if(key!=null&&!"".equals(key)) {
                builder.append(" and " + key + " like ? ");
                String value = condition.get(key)[0];//根据键得到值
                params.add("%" + value + "%");
            }
        }
        sql=builder.toString();
//        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public void add(User user) {
        String sql="INSERT into user(name,gender,age,address,qq,email)VALUES(?,?,?,?,?,?);";
        jdbcTemplate.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delUser(int id) {
        String sql="delete from user where id=?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public User findUserById(String uid) {
        return jdbcTemplate.queryForObject("select * from  user where id=?",new BeanPropertyRowMapper<User>(User.class),uid);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("update user set name=?,gender=?,age=?,email=?,qq=?,address=? where id=?",user.getName(),user.getGender(),user.getAge(),user.getEmail(),user.getQq(),user.getAddress(),user.getId());
    }
}
