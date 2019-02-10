package com.blueHouse.mapper;

import com.blueHouse.pojo.orders.Order;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface OrderMapper {
    Order findOrderById(String id);
    List<Order> findOrderByUserId(String id);
    List<Order> findOrderByUserName(String name);
    void insertOrder(Order order);
    void updateOrder(Order order);
    void updateOrderPhone(Order order);
    void updateOrderStatus(Order order);
}
