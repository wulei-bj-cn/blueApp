package com.blueHouse.test;

import com.blueHouse.pojo.orders.Material;
import com.blueHouse.service.MaterialService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestMaterialService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findMaterialById() throws Exception {
        MaterialService materialService= (MaterialService) applicationContext.getBean("materialService");
        Material material = materialService.finaMaterialById("mat1");
        System.out.println(material.getId());
    }
}
