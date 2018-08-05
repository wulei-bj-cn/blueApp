package com.blueHouse.test;

import com.blueHouse.mapper.UserMapper;
import com.blueHouse.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestSpring {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findUserByID() throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById("1");
        System.out.println(user.getName());
    }

    @Test
    public void findAllUsers() throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        List<User> users = userMapper.findAllUsers();
        for (User user: users) {
            System.out.println(user.getName());
        }
    }
}
