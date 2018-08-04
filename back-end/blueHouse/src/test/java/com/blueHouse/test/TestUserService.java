package com.blueHouse.test;

import com.blueHouse.pojo.User;
import com.blueHouse.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestUserService {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findUserByID() throws Exception {
        UserService userService = (UserService) applicationContext.getBean("userService");
        User user = userService.findUserById(1);
        System.out.println(user.getName());
    }

    @Test
    public void findUserByPartialId() throws Exception {
        UserService userService = (UserService) applicationContext.getBean("userService");
        List<User> users = userService.findUserByPartialId(1);
        for (User user: users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void findUserByName() throws Exception {
        UserService userService = (UserService) applicationContext.getBean("userService");
        List<User> users = userService.findUserByName("吴");
        for (User user: users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void findAllUsers() throws Exception {
        UserService userService = (UserService) applicationContext.getBean("userService");
        List<User> users = userService.findAllUsers();
        for (User user: users) {
            System.out.println(user.getName());
        }
    }
}
