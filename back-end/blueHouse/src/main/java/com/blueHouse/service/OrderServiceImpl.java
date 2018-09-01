package com.blueHouse.service;

import com.blueHouse.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class OrderServiceImpl implements  OrderService{
    @Resource
    @Autowired
    private OrderService orderService;

    @Override
    public Order findOrderById(String id) {
        return orderService.findOrderById(id);
    }

    @Override
    public List<Order> findOrderByUserId(String id) {
        return orderService.findOrderByUserId(id);
    }

    @Override
    public List<Order> findOrderByUserName(String name) {
        return orderService.findOrderByUserName(name);
    }

    @Override
    public void insertOrder(Order order) {
        orderService.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }
}
