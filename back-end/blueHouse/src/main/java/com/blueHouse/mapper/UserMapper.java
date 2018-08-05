package com.blueHouse.mapper;

/**
 * Created by wulei on 23/07/2018.
 */
import java.util.List;

import com.blueHouse.pojo.User;

public interface UserMapper {
    User findUserById(String id);
    List<User> findUserByPartialId(String id);
    List<User> findUserByNameOrID(String name);
    List<User> findAllUsers();
    void insertUser(User user);
    void updateUser(User user);
}
