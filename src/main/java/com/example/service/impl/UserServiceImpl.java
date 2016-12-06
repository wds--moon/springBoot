package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liaoqianyang on 2016/10/25.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    //@Cacheable(value = "user",key = "#id")
    @Override
    public User getUserById(Integer id) {
        System.out.println("++++++++++++++++++++++"+id);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void addAndUpdateUser() {
        User user1 = new User("11111111111", "000000000000000");
        User user2 = new User(11, "1111111111111111");
        int insertNum = userMapper.insert(user1);
        int updateNum = userMapper.updateById(user2);
        logger.info("insertNum={},updateNum={}", insertNum, updateNum);
        if (updateNum > 0) throw new RuntimeException("抛出异常, 回滚事物");
    }

}
