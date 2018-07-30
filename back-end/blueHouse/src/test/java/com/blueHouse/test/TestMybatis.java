package com.blueHouse.test;

import org.junit.Test;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.blueHouse.mapper.UserMapper;
import com.blueHouse.pojo.User;
/**
 * Created by wulei on 23/07/2018.
 */
public class TestMybatis {
    @Test
    public void findUserByID() throws Exception{
        String resource = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        //---------------
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserById(1);
        System.out.println(user.getName());
        //--------------
        session.close();
    }

    @Test
    public void findAllUsers() throws Exception{
        String resource = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        //---------------------
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.findAllUsers();
        for (User user: users) {
            System.out.println(user.getName());
        }
        //----------------------
        session.close();
    }

    @Test
    public void insertTest() throws Exception{
        String resource = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        //---------------------
        User user = new User();
        user.setId(23);
        user.setName("23lalala");

        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.insertUser(user);
        session.commit();
        //----------------------
        session.close();
    }
}
