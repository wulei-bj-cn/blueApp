package com.blueHouse.service;


import com.blueHouse.pojo.orders.OrderItem;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface OrderItemService {
    List<OrderItem> findOrderItemByOrderId(String order_id);
    OrderItem findOrderItemBy2Id(OrderItem orderItem);
    void insertOrderItem(OrderItem orderItem);
    void updateOrderItemStatus(OrderItem orderItem);
    void updateOrderItemEndTime(OrderItem orderItem);
    void deleteOrderItem(OrderItem orderItem);
    void deleteOrderItemsByOrder(OrderItem orderItem);
}
