package com.blueHouse.test;

import com.blueHouse.pojo.browse.T_Disclaim;
import com.blueHouse.service.DisclaimService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestDisclaimService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findDisclaimById() throws Exception {
        DisclaimService disclaimService= (DisclaimService) applicationContext.getBean("disclaimService");
        T_Disclaim Disclaim = disclaimService.findDisclaimById("dis1");
        System.out.println(Disclaim.getId());
    }
}
