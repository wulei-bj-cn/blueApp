package com.blueHouse.test;

import com.blueHouse.pojo.browse.T_Measure;
import com.blueHouse.service.MeasureService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestMeasureService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findMeasureById() throws Exception {
        MeasureService measureService= (MeasureService) applicationContext.getBean("measureService");
        T_Measure measure = measureService.findMeasureById("mea1");
        System.out.println(measure.getId());
    }
}
