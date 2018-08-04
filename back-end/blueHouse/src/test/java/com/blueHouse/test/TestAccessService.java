package com.blueHouse.test;

import com.blueHouse.pojo.Access;
import com.blueHouse.service.AccessService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestAccessService {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findAccessByID() throws Exception {
        AccessService accessService = (AccessService) applicationContext.getBean("accessService");
        Access access = accessService.findAccessById(1);
        System.out.println(access.getId());
    }

    @Test
    public void findAccessByPartialId() throws Exception {
        AccessService accessService = (AccessService) applicationContext.getBean("accessService");
        List<Access> accesss = accessService.findAccessByPartialId(1);
        for (Access access: accesss) {
            System.out.println(access.getStart_time());
        }
    }

    @Test
    public void findAllAccesss() throws Exception {
        AccessService accessService = (AccessService) applicationContext.getBean("accessService");
        List<Access> accesss = accessService.findAllAccesss();
        for (Access access: accesss) {
            System.out.println(access.getEnd_time());
        }
    }
}
