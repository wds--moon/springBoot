package com.example.mapper;

import com.example.entity.User;

import java.util.List;

/**
 * Created by liaoqianyang on 2016/10/25.
 */
public interface UserMapper  {

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int insert(User user);

    int updateById(User user);
}
