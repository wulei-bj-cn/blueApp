package com.blueHouse.mapper;

/**
 * Created by wulei on 23/07/2018.
 */
import java.util.List;

import com.blueHouse.pojo.User;

public interface UserMapper {
    User findUserById(int id);
    List<User> findUserByName(String name);
    List<User> findAllUsers();
    void insertUser(User user);
}
