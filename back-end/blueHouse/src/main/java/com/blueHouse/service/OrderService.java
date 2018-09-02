package com.blueHouse.service;

import com.blueHouse.pojo.Order;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface OrderService {
    Order findOrderById(String id);
    List<Order> findOrderByUserId(String id);
    List<Order> findOrderByUserName(String name);
    void insertOrder(Order order);
    void updateOrder(Order order);
}
