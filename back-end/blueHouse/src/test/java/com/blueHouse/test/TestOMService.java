package com.blueHouse.test;

import com.blueHouse.pojo.Measure;
import com.blueHouse.pojo.Order;
import com.blueHouse.service.OMService;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestOMService {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findMeasure() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Measure> measures = omService.findMeasure("usr8", "ord1");
        for (Measure measure: measures) {
            System.out.println(
                    measure.getId() + "\n" +
                            measure.getUser_id() + "\n" +
                            measure.getOrder_id() + "\n" +
                            measure.getName() + "\n" +
                            measure.getUrl() + "\n" +
                            measure.getTs() + "\n" +
                            measure.getCrew()
            );
        }
    }

    @Test
    public void findOrder() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Order> orders = omService.findOrder("usr8", "ord1");
        for (Order order: orders) {
            System.out.println(
                    order.getUser_id() + "\n" +
                            order.getId() + "\n" +
                            order.getStart_time() + "\n" +
                            order.getEnd_time() + "\n" +
                            order.getStatus()
            );
        }
    }
}
