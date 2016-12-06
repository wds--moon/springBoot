package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * Created by liaoqianyang on 2016/10/25.
 */
public interface IUserService {

    User getUserById(Integer id);

    List<User> findAll();

    void addAndUpdateUser();
}
