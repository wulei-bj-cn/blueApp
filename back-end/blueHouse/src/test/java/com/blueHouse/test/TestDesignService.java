package com.blueHouse.test;

import com.blueHouse.pojo.orders.Contract;
import com.blueHouse.pojo.orders.Design;
import com.blueHouse.service.DesignService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestDesignService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findDesignById() throws Exception {
        DesignService designService= (DesignService) applicationContext.getBean("designService");
        Design design = designService.findDesignById("des1");
        System.out.println(design.getId());
    }
}
