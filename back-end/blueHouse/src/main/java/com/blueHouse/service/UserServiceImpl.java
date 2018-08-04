package com.blueHouse.service;

import com.blueHouse.mapper.UserMapper;
import com.blueHouse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public List<User> findUserByPartialId(int id) {
        return userMapper.findUserByPartialId(id);
    }

    public List<User> findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }
}
