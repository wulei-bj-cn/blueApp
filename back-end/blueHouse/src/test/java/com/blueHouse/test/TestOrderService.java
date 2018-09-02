package com.blueHouse.test;

import com.blueHouse.pojo.orders.Order;
import com.blueHouse.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestOrderService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findOrderById() throws Exception {
        OrderService orderService= (OrderService) applicationContext.getBean("orderService");
        Order order = orderService.findOrderById("ord1");
        System.out.println(order.getId());
    }
}
