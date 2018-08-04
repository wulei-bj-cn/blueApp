package com.blueHouse.service;

import com.blueHouse.pojo.User;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public interface UserService {
    User findUserById(int id);
    List<User> findUserByPartialId(int id);
    List<User> findUserByName(String name);
    List<User> findAllUsers();
}
