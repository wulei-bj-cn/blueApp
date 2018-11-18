package com.blueHouse.service;

import com.blueHouse.mapper.OrderMapper;
import com.blueHouse.pojo.orders.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class OrderServiceImpl implements  OrderService{
    @Resource
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order findOrderById(String id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public List<Order> findOrderByUserId(String id) {
        return orderMapper.findOrderByUserId(id);
    }

    @Override
    public List<Order> findOrderByUserName(String name) {
        return orderMapper.findOrderByUserName(name);
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public void updateOrderStatus(Order order) { orderMapper.updateOrderStatus(order); }
}
